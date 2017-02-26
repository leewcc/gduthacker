package com.hackerspace.action.view.publicPart;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;

import com.hackerspace.dao.BaseTDao;
import com.hackerspace.dao.ProjectDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Project;
import com.hackerspace.model.TeamUser;
import com.hackerspace.model.User;
import com.hackerspace.util.Log;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("struts-default")
@Namespace("/user/project")
public class ProjectAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Log.get();
	private ProjectDao pDao = new ProjectDao();
	
	public Project project;
	
	@Action(value="query", results={
			@Result(name="success", location="/Web/jsp/View/Public/Project/ShowProject.jsp"),
			@Result(name="error", location="/Web/jsp/Result/Error.jsp")})
	public String queryProjects() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Byte bTag = null;
		String tag = hsq.getParameter("tag");
		
		try {
			bTag = Byte.valueOf(tag);
		} catch (Exception e) {
			bTag = new Byte((byte)1);
		}
		
		PageElem<Project> pageElem = new PageElem<Project>();
		
		pageElem.setCurrentPage(hsq.getParameter("cp"));
		pageElem.setPageShow(10);
		
		Log.debug(log, "queryProjects()方法参数; tag={};currentPage={}", bTag, pageElem.getCurrentPage());
		
		try {
			pDao.queryProjectAll(pageElem, bTag);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
			
		hsq.setAttribute("pageElem", pageElem);
		hsq.setAttribute("tag", tag);
		
		return SUCCESS;
	}

	
	@Action(value="q", results={
			@Result(name="success", location="/Web/jsp/View/Public/Project/ProjectCont.jsp")
	})
	public String queryProject() throws IOException {
		HttpServletRequest hsq =  ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		BaseTDao<User> b = new BaseTDao<User>();
		BaseTDao<TeamUser> tu = new BaseTDao<TeamUser>();
		Project project = null;
		User user = null;
		TeamUser teamUser;
		try {
			// 获取项目project信息
			project = pDao.queryOne(id);
			// 获取项目的团队和负责人信息表
			teamUser = tu.find(TeamUser.class, project.getTeamId());
			// 获取负责人信息
			if(teamUser != null) {
				user = b.find(User.class, teamUser.getUser().getId());
			}
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		
		hsq.setAttribute("project", project);
		// 若无负责人, 则null
		hsq.setAttribute("user", user);

		
		return SUCCESS;
	}
}
