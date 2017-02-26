package com.hackerspace.action.view.high;

import java.util.List;
import java.util.Map;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamUser;
import com.hackerspace.model.User;
import com.hackerspace.service.TeamService;
import com.hackerspace.service.TeamUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TeamAction extends ActionSupport{
	private int id;
	private int page;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String selectMyTeam() throws Exception{
		
		// 第一步： 获取用户
		ActionContext ac = ActionContext.getContext();
		Map ses = ac.getSession();
		User u = (User) ses.get("user");

		// 第二步： 创建团队用户服务
		TeamUserService tus = new TeamUserService();

		// 获取我的团队
		List<TeamUser> teams = tus.selectMyTeam(u);

		Map req = (Map) ac.get("request");
		req.put("teams", teams);

		return SUCCESS;

	}
	
	public String seeMyTeam() {
		try{
			//第一步：	创建团队服务、团队人员
			TeamService ts = new TeamService();
			
			//第二步：	获取团队
			Team t = ts.find(id);
			
			
			//第三步：	保存进请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("team", t);

			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
}


