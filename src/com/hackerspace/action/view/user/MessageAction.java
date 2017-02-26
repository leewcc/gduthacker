package com.hackerspace.action.view.user;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;
import com.hackerspace.service.MessageService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;

public class MessageAction extends ActionSupport implements ModelDriven{
	private Message m;
	private int page;
	
	public Message getM() {
		return m;
	}

	public void setM(Message m) {
		this.m = m;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public String sendMess() {
		try{			
			//第一步：	创建留言服务
			MessageService ms = new MessageService();
			
			//第二步：	获取用户信息
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			User u = (User)ses.get("user");
			
			String content = m.getContent();
			if(content == null || "".equals(content)){
				req.put("message", "请输入你要留言的信息");
				return "fail";
				 
			}else{
				if(content.length() > 250){
					req.put("message", "留言信息不能超过250个字");
					return "fail";
				}
					
			}
			
			//第二步：	执行留言的方法
			m.setUser(u);
			m.setDate(new Timestamp(new Date().getTime()));
			ms.SendMessage(m);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String selectMyMess(){
		try{
			//第一步：	获取用户
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			User u = (User)ses.get("user");
			
			//第一步：	创建留言服务
			MessageService ms = new MessageService();
			
			//第二步：	执行留言的方法
			PageElem<Message> pe = ms.selectMyMess(u, page);
			
			//第三步：	保存到请求
			Map req = (Map)ac.get("request");
			req.put("messages", pe);
			
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}

	public String messageBox() {
		try{
			MessageService ms = new MessageService();
			
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			User u = (User)ses.get("user");
			
			PageElem<Message> mess = ms.messageBox(page, u);
			List<Message> me = mess.getPageElem();
			for(Message m : me){
				m.setStatus(4);
			}
			
			ms.update(me);
			
			req.put("messages", mess);
			return SUCCESS;
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String findMessage() {
		try{
			//第一步：	创建留言服务
			MessageService ms = new MessageService();
			
			//第二步：	获取留言
			m = ms.find(m.getId());
			
			//第三步：	保存在请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("message", m);
			
			return SUCCESS;
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	@Override
	public Object getModel() {
		if(m == null)
			m = new Message();
		return m;
	}
}
