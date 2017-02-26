package com.hackerspace.service;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.TeamNewsDao;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamNews;

public class TeamNewsService {
	private BaseDao bd = new BaseDao<>();
	
	public TeamNewsService() {
		bd = new BaseDao<>();
	}
	
	public void create(TeamNews news) {
		bd.create(news);
	}
	
	public void update(TeamNews news) {
		bd.update(news);
	}
	
	public TeamNews find(int id) {
		return (TeamNews)bd.find(TeamNews.class, id);
	}
	
	public void delete(int id) {
		TeamNews news = find(id);
		bd.delete(news);
	}
	
	public void handle(int id, boolean isPass) {
		TeamNews news = find(id);
		if(isPass)
			news.setStatus(1);
		else
			news.setStatus(2);
		
		update(news);
	}
	
	public PageElem<TeamNews> selectTeamNews(int page, Team t, int status) throws Exception{
		PageElem<TeamNews> pe = new PageElem<>();
		pe.setCurrentPage(page);
		pe.setPageShow(10);
		pe.setRows(TeamNewsDao.selectCount(t, status));
		pe.setPageElem(TeamNewsDao.selectNews(t, pe.getStartSearch(), pe.getPageShow(), status));
		return pe;
	}
}
