package com.hackerspace.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.TeamNewsDao;
import com.hackerspace.dao.TeamUserDao;
import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamNews;
import com.hackerspace.model.TeamUser;
import com.hackerspace.model.User;

public class TeamUserService {
private BaseDao bd = new BaseDao<>();
	
	public TeamUserService() {
		bd = new BaseDao<>();
	}
	
	public void create(TeamUser user) {
		bd.create(user);
	}
	
	public void update(TeamUser user) {
		bd.update(user);
	}
	
	public TeamUser find(int id) {
		return (TeamUser)bd.find(TeamUser.class, id);
	}
	
	public void delete(TeamUser user) {
		bd.delete(user);
	}
	
	public void handle(int id, boolean isPass) {
		TeamUser user = find(id);
				
		if(isPass){
			if(user.getStatus() == 2){
				user.setStatus(5);
				
			}else
				user.setStatus(4);
			
		}else
			if(user.getStatus() ==2)
				user.setStatus(4);
			else {
				delete(user);
				return;
			}
		update(user);
		
	}
	
	public PageElem<TeamUser> selectTeamNews(int page, Team t, boolean isMember) throws Exception{
		PageElem<TeamUser> pe = new PageElem<>();
		pe.setCurrentPage(page);
		pe.setPageShow(10);
		pe.setRows(TeamUserDao.selectCount(t, isMember));
		pe.setPageElem(TeamUserDao.selectByTeam(t, pe.getStartSearch(), pe.getPageShow(), isMember));
		return pe;
	}
	
	public List<TeamUser> selectMyTeam(User u) throws Exception{
		return TeamUserDao.selectMyTeam(u);
	}
	
	public TeamUser getStatus(User u, Team t) throws Exception{
		return TeamUserDao.getStatus(u, t);
	}
	
	public TeamUser getManager(Team t) throws Exception{
		return TeamUserDao.getManager(t);
	}
	
	public int getTeamMemberCount(Team t) throws Exception{
		return TeamUserDao.getTeamMemberNumber(t);
	}
}
