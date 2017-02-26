package com.hackerspace.service;

import java.util.ArrayList;
import java.util.List;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.TeamDao;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.User;

public class TeamService {
	private BaseDao bd = new BaseDao<>();
	
	public TeamService() {
		bd = new BaseDao<>();
	}
	
	public void create(Team t) throws Exception {
		bd.create(t);
	}
	
	public void update(Team t) throws Exception {
		bd.update(t);
	}
	
	public void delete(int id) throws Exception{
		Team t = find(id);
		t.setStatus(0);
		bd.update(t);
	}
	
	public Team find(int id) throws Exception{
		return (Team)bd.find(Team.class, id);
	}
	
	public Team getTeam(int id) throws Exception{
		return TeamDao.getTeam(id);
	}
	
	public PageElem<Team> selectTeam(int page, int status) throws Exception{
		PageElem<Team> pe = new PageElem<>();
		pe.setCurrentPage(page);
		pe.setPageShow(10);
		pe.setRows(TeamDao.selectCount(status));
		pe.setPageElem(TeamDao.selectTeam(pe.getStartSearch(), pe.getPageShow(), status));
		return pe;
	}

	public boolean updateTeamPicture(Team t) {
		// TODO Auto-generated method stub
		TeamDao td=new TeamDao();
		return td.updateTeamPicture(t);
	}
	
	public List<Team> getTeamView() {
		try{
			return TeamDao.selectTeam(0, 3, 1);
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public boolean deleteTeam(Team team) {
		return TeamDao.deleteTeam(team);
	}
	
	
}
