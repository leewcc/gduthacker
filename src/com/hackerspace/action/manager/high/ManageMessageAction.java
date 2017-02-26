package com.hackerspace.action.manager.high;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.context.ManagedSessionContext;

import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;
import com.hackerspace.service.MessageService;
import com.hackerspace.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.accessibility.internal.resources.accessibility;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class ManageMessageAction extends ActionSupport{
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * 方法说明：	查询未被回复的留言
	 * @return
	 */
	public String selectMessage() {
		try{
			//第一步：	创建留言服务
			MessageService ms = new MessageService();
			
			//第二步：	获取留言
			PageElem<Message> pe = ms.selectMessage(page);
			
			//第三步：	将留言保存进请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("messages", pe);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	
	private int id;
	private String content;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String findMessage() throws Exception{
		try{
			//第一步：	创建留言服务
			MessageService ms = new MessageService();
			
			//第二步：	获取留言
			Message m = ms.find(id);
			
			//第三步：	将留言保存进请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("message", m);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	
	/**
	 * 方法说明：	回复留言
	 * @return
	 */
	public String replyMessage(){
		try{
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			
			if(content == null || "".equals(content)){
				req.put("error", "请输入留言信息");
				return "fail";
			}else{
				if(content.length() > 250){
					req.put("error", "回复内容不能超过250个字");
					return "fail";
				}
			}
			
			//第一步：	创建留言对象
			Message m = new Message();
			m.setContent(content);
			m.setStatus(2);
			m.setDate(new Timestamp(new Date().getTime()));
			
			//第二步：	创建留言回复
			MessageService ms = new MessageService();
			
			//第三步：	执行回复留言方法
			ms.replyMessage(id, m);
			
			return "success";

		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 方法说明：	删除留言
	 * @return
	 */
	public String deleteMessage() {
		try{
			//第一步：	创建留言服务
			MessageService ms = new MessageService();
			
			//第二步：	执行删除留言的方法
			ms.deleteMessage(id);
			
			return "success";
		
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	private List<Integer> user;
	


	public List<Integer> getUser() {
		return user;
	}

	public void setUser(List<Integer> user) {
		this.user = user;
	}


	private int role;
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String findUser() {
		try{
			UserService us = new UserService();
			
			List<User> users = us.selectByRole(role);
			
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			
			req.put("users", users);
			
			return SUCCESS;
					
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public void validateMessToUser() {
		if(content == null || "".equals(content))
			this.addFieldError("content", "请输入你要发送的信息");
		
		if(user == null || user.isEmpty()) 
			this.addFieldError("user", "请选择你要发送信息的用户");
	}
	
	/**
	 * 方法说明：	
	 * @return
	 */
	public String  messToUser() {
		try{
			//第一步：	创建留言服务
			MessageService ms = new MessageService();
		
			HttpServletRequest req = ServletActionContext.getRequest();
			
			
			//第二步：	执行给用户留言的方法
			ms.messToUser(content, user);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
}
