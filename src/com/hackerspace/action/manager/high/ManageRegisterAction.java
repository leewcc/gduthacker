package com.hackerspace.action.manager.high;

import java.util.Map;

import javax.swing.JEditorPane;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;
import com.hackerspace.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ManageRegisterAction extends ActionSupport implements ModelDriven{
	//第一个：	获取请求注册的用户
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public String selectRegister(){
		try{
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	获取用户
			PageElem<User> pe = us.selectRegister(page);
			
			//第三步：	存放到Map里面，返回注册审核页面
			ActionContext context = ActionContext.getContext();
			Map request = (Map)context.get("request");
			request.put("users", pe);
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("获取用户注册请求失败");
			return "error";
		}		
	}
	

	//第二个：	处理注册请求
	private boolean pass;
	private int id;
	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String handleRegister(){
		try{
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	处理用户请求
			return us.handleRegister(id, pass);
			
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("审核注册请求出错");
			return "error";
		}
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
