package com.hackerspace.action.view.high;

import java.util.Map;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamUser;
import com.hackerspace.model.User;
import com.hackerspace.service.TeamService;
import com.hackerspace.service.TeamUserService;
import com.hackerspace.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TeamUserAction extends ActionSupport{
	private int page;
	private int pageN;
	private int id;
	private int tid;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
			
			return SUCCESS;
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	private String card;
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	
	public void validateFindMember() {
		if(card == null || "".equals(card))
			this.addFieldError("card", "请输入学号");
		else{
			int num = card.length();
			if(num < 6 || num > 15)
				this.addFieldError("card", "学号只能6-15位");
		}
	}
	
	public String findMember(){
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
	
	public String addMember() {
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
				case 1:
					req.put("message", "该用户已正在申请加入团队，请耐心等候审核");
					return "fail";
				case 2:
					req.put("message", "该用户已正在申请脱离团队，请耐心等候审核");
					return "fail";
				case 3:
					req.put("message", "该用户已经是团队负责人");
					return "fail";
				case 4:
					req.put("message", "该用户已经是团队成员");
					return "fail";
				case 5:
					tus.delete(tu);
					break;

				default:
					break;
				}
			}
			
			tu = new TeamUser();
			tu.setTeam(t);
			tu.setUser(u);
			tu.setStatus(1);
			tus.create(tu);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String cancel() {
		try{
			TeamUserService tus = new TeamUserService();
			
			TeamUser tu = tus.find(id);
			
			if(tu.getStatus() == 1){
				tus.delete(tu);
			}else{
				tu.setStatus(4);
				tus.update(tu);
			}
			
			return SUCCESS;
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String leaveTeam() {
		try{
			TeamUserService tus = new TeamUserService();
			
			TeamUser tu = tus.find(id);
			
			tu.setStatus(2);
			
			tus.update(tu);
			
			return SUCCESS;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
