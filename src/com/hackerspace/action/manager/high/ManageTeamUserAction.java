package com.hackerspace.action.manager.high;

import java.security.spec.DSAGenParameterSpec;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamUser;
import com.hackerspace.model.User;
import com.hackerspace.service.TeamService;
import com.hackerspace.service.TeamUserService;
import com.hackerspace.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ManageTeamUserAction extends ActionSupport{
	private int id;
	private int tid;
	private int page;		//成员的页数
	private int pageN;	 //待审核成员的页数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageN() {
		return pageN;
	}
	public void setPageN(int pageN) {
		this.pageN = pageN;
	}
	
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String selectTeamUser() {
		try{
			//第一步：	创建团队用户服务、团队服务
			TeamService ts = new TeamService();
			TeamUserService tus = new TeamUserService();
			
			//第二步：	获取团队
			Team t = ts.find(tid);
			
			//第三步：	获取已是成员和待审核成员的用户
			PageElem<TeamUser> members = tus.selectTeamNews(page, t, true);
			PageElem<TeamUser> membersN = tus.selectTeamNews(pageN, t, false);
			
			//第四步：	将数据存放到请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("team", t);
			req.put("members", members);
			req.put("membersN", membersN);
			
			return "success";
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	private boolean isPass;
	public boolean isPass() {
		return isPass;
	}
	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
	
	public String handleTeamUser() {
		try{
			//第一步：	创建团队用户服务
			TeamUserService tus = new TeamUserService();
			
			//第二步：	执行处理用户入队 退队请求
			tus.handle(id, isPass);
					
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	private String card;	//负责人学号
	
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	
	public void validateFindPerson() {
		if(card == null || "".equals(card))
			this.addFieldError("card", "请输入学号");
		else{
			int num = card.length();
			if(num < 6 || num > 15)
				this.addFieldError("card", "学号只能6-15位");
		}
	}
	
	public String findPerson() {
		try{
			UserService us = new UserService();
			
			User user = us.getUser(card);
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			if(user == null){
				req.put("error", "找不到该用户");
				return "null";
			}else{				
				req.put("user", user);
				return SUCCESS;
			}
						
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String addPerson() {
		try{
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			
			//第一步：	创建团队服务、用户服务、团队用户服务
			TeamService ts = new TeamService();
			UserService us = new UserService();
			TeamUserService tus = new TeamUserService();
			
			//第二步：	获取团队、用户
			Team t = ts.find(tid);
			User u = us.getUser(card);
			
TeamUser tu = tus.getStatus(u, t);
			
			if(tu != null){
				switch (tu.getStatus()) {
				case 3:
					req.put("message", "该用户已经是团队负责人");
					return "fail";

				default:
					tus.delete(tu);
					break;
				}
			}
			
			tu = new TeamUser();
			tu.setTeam(t);
			tu.setUser(u);
			tu.setStatus(3);
			tus.create(tu);
			
			u.setRole(2);
			us.update(u);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
}
