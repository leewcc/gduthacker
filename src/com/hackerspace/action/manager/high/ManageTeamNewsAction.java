package com.hackerspace.action.manager.high;

import java.util.Map;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamNews;
import com.hackerspace.model.TeamUser;
import com.hackerspace.service.TeamNewsService;
import com.hackerspace.service.TeamService;
import com.hackerspace.service.TeamUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ManageTeamNewsAction extends ActionSupport{
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
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
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
	public String selectTeamNews() {
		try{
			//第一步：	创建团队用户服务、团队服务
			TeamService ts = new TeamService();
			TeamNewsService tns = new TeamNewsService();
			
			//第二步：	获取团队
			Team t = ts.find(tid);
			
			//第三步：	获取已是成员和待审核成员的用户
			PageElem<TeamNews> news = tns.selectTeamNews(page, t, 1);
			PageElem<TeamNews> newsN = tns.selectTeamNews(page, t, 0);
			
			//第四步：	将数据存放到请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("team", t);
			req.put("news", news);
			req.put("newsN", newsN);
			
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
	
	public String handleTeamNews() {
		try{
			//第一步：	创建团队用户服务
			TeamNewsService tns = new TeamNewsService();
			
			//第二步：	执行处理用户入队 退队请求
			tns.handle(id, isPass);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
}
