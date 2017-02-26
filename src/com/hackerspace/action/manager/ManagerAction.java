package com.hackerspace.action.manager;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.struts2.ServletActionContext;

import com.hackerspace.dao.UserDao;
import com.hackerspace.model.User;
import com.hackerspace.service.UserService;
import com.hackerspace.util.CookieUtils;
import com.hackerspace.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ManagerAction extends ActionSupport implements ModelDriven{
	private User user;
	private String code;
 	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	private CookieUtils cookieUtils=new CookieUtils();
	private UserDao userDAO =new UserDao();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void validateLogin(){
		String card = user.getCard();
		String password = user.getPassword();
		
		if(card == null || "".equals(card))
			this.addFieldError("card", "请输入学号");
		else{
			int num = card.length();
			if(num < 6 || num > 15)
				this.addFieldError("card", "学号只能6-15位");
		}
		
		if(password == null || "".equals(password))
			this.addFieldError("password", "请输入密码");
		else {
			int num = password.length();
			if(num < 6 || num > 15)
				this.addFieldError("password", "密码只能5-15位");
		}
		
		if(code == null || "".equals(code))
			this.addFieldError("code", "请输入验证码");
	}
	
	
	//第一个：	登陆
	public String login(){
		try{
			
			ActionContext ac = ActionContext.getContext();
			Map res = ac.getSession();
			Map req = (Map)ac.get("request");
		
			String rand = (String)res.get("rand");
			
			if(rand != null)
				rand = rand.toLowerCase();
			
			code = code.toLowerCase();
			
			if(!code.equals(rand)){
				req.put("codeE","验证码不正确");
				return INPUT;
			}
			
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	执行登陆操作
			User u = us.login(user.getCard(),MD5Util.GetMD5Code(user.getPassword()));
			
			//第三步：	判断是否存在用户，没有则登陆失败
			if(u == null  ){
				req.put("error", "用户名或密码错误");
				return "login";
			}
			
			//第四步：	判断该用户是否已通过审核
			if(u.getRole() <= 3){
				req.put("message", "很抱歉，您不能登录此页面");
				req.put("url", "/GDUTHackerSpace/Web/jsp/View/Public/User/Login.jsp");
				return "fail";
			}
			
			//第五步：	登陆成功，将用户保存进sessin
			res.put("manager", u);
			
			
//			String cookieFlag= request.getParameter("cookieFlag");
//			if("1".equals(cookieFlag)){
//			//存进cookie中
//				Cookie cookie = cookieUtils.addCookie(u);
//				response.addCookie(cookie);
//			}
			
			return "success";
		
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String goPassword() {
		return SUCCESS;
	}
	
	public String logout() {
		try{
			//第一步：	获取Session
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			ses.remove("manager");
			Cookie cookie =cookieUtils.delCookie(request);
			response.addCookie(cookie);
			return SUCCESS;
			
		}catch(Exception e){
			e.getMessage();
			return ERROR;
		}
	}
	
	@Override
	public Object getModel() {
		if(user == null)
			user = new User();
		
		return user;
	}
}
