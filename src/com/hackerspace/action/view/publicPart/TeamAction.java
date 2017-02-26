package com.hackerspace.action.view.publicPart;

import java.util.Map;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamUser;
import com.hackerspace.service.TeamService;
import com.hackerspace.service.TeamUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TeamAction extends ActionSupport{
	private int page;
	private int id;
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
	
	public String selectTeam() {
		try{
			//第一步：	创建团队服务
			TeamService ts = new TeamService();
			
			//第二步：	执行查询仍存在团队和已删除团队的方法
			PageElem<Team> teams = ts.selectTeam(page, 1);
			
			//将对象保存在请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("teams", teams);
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String seeTeam() {
		try{
			//第一步：	创建团队服务
			TeamService ts = new TeamService();
			
			//第二步：	获取团队
			Team t = ts.find(id);

			
			//第三步：	将团队存进请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("team", t);
			
			//第四步：	根据不同的操作调用相应的方法
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
}
