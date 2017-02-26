package com.hackerspace.action.manager.high;

import java.util.Map;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Power;
import com.hackerspace.model.User;
import com.hackerspace.service.PowerSerivce;
import com.hackerspace.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ManageUserAction extends ActionSupport implements ModelDriven{
	private int page;		//页数
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	//第一个：	获取指定角色的用户
	public String seleceByRole(){
		try{
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	获取用户
			PageElem<User> pe = us.selectByRole(u.getRole(), page);
			
			//第三步：	存放到Map里面，返回注册审核页面
			ActionContext context = ActionContext.getContext();
			Map request = (Map)context.get("request");
			request.put("users", pe);
			
			String out = u.getRole() <= 2? "common" : "high";
			
			return out;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("根据角色获取用户失败");
			return "error";
		}
	}
	
	//第二个：	获取指定用户
	public String getUser(){
		try{
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	获取用户
			u = us.getUser(u.getId());
			
			//第三步：	判断用户是否存在
			if(u == null)
				return "null";
			
			//第四步：	存放到Map里面，返回注册审核页面
			ActionContext context = ActionContext.getContext();
			Map request = (Map)context.get("request");
			request.put("user", u);
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("根据id获取用户失败");
			return "error";
		}
	}
	
	//第三个：	删除用户
	public String delete(){
		try{
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	删除用户
			if(!us.delete(u.getId())){
				ActionContext ac = ActionContext.getContext();
				Map req = (Map)ac.get("request");
				req.put("message", "请先删除与该用户相关的信息");
			}
				
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("根据id删除用户失败");
			return "error";
		}
	}
	
	public String resetPass() {
		try{
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	删除用户
			us.resetPassword(u.getId());
			
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("message", "重置成功");
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("根据id删除用户失败");
			return "error";
		}
	}
	
	private User u;
	
	
	@Override
	public Object getModel() {
		if(u == null)
			u = new User();
		
		return u;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	
	public void validateAddUser() {
		String name = u.getName();
		String card = u.getCard();
		String idCard = u.getIdCard();
		String password = u.getPassword();
		int role = u.getRole();
		
		if(idCard == null || "".equals(idCard))
			this.addFieldError("idCard", "请输入身份证");	
		else {
			int num = idCard.length();
			if(num != 18)
				this.addFieldError("idCard", "请输入正确的身份证");
		}
		
		if(card == null || "".equals(card))
			this.addFieldError("card", "请输入学号");
		else{
			int num = card.length();
			if(num < 6 || num > 15)
				this.addFieldError("card", "学号只能6-15位");
		}
		
		if(name == null || "".equals(name))
			this.addFieldError("name", "请输入姓名");
		
		if(!(role == 3 || role == 4 || role == 5))
			this.addFieldError("role", "请选择你要添加的用户类型");
		
		if(password == null || "".equals(password))
			this.addFieldError("password", "请输入密码");
		else {
			int num = password.length();
			if(num < 6 || num > 15)
				this.addFieldError("password", "密码只能5-15位");
		}
	}
	
	public String addUser() {
		try{
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			
			UserService us = new UserService();
			PowerSerivce ps = new PowerSerivce();
			u.setRequest(3);
			if(u.getRole() > 3){
				Power p = new Power();
				p.setUser(u);
				if(u.getRole() == 5){
					p.setCanMessage(true);
					p.setCanPicture(true);
					p.setCanSerivce(true);
					p.setCanSystem(true);
					p.setCanUser(true);
				}
				
				ps.create(p);
			}
			
			String result = us.register(u);
			if("exist".equals(result)){
				req.put("error", "该工号/雪好已存在");
				return "exist";
			}
			
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
}
