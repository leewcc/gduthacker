package com.hackerspace.action.manager.superm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.Link;
import com.hackerspace.model.News;
import com.hackerspace.model.Picture;
import com.hackerspace.model.Team;
import com.hackerspace.service.NewsService;
import com.hackerspace.service.PictureService;
import com.hackerspace.service.ProgramService;
import com.hackerspace.service.TeamService;
import com.opensymphony.xwork2.ActionSupport;

public class HomePageAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String showHomePage() {
		HttpServletRequest request=ServletActionContext.getRequest();
		/*首页新闻*/
		NewsService ns=new NewsService();
		List <News>list =(ArrayList<News>)ns.findTopNews();
		for(News n:list) {
			String content=n.getContent();
			if(content.contains("img")) {
				int s=content.indexOf("src=\"")+5;
				int e=content.indexOf("\"",s);
				n.setImageUrl(content.substring(s,e));
			}
			else {
				n.setImageUrl("默认图片.jpg");
			}
		} 
		request.setAttribute("newsList",list);//新闻
		/*团队*/
		TeamService ts = new TeamService();
		List<Team> teams = ts.getTeamView();
		
		/*轮播图片*/
		PictureService pis=new PictureService();
		List<Picture> pl=pis.findPicture("1");
		request.setAttribute("pictureList", pl);
		request.setAttribute("teams", teams);
		return SUCCESS;
	}
	
}
