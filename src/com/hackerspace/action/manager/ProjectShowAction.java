package com.hackerspace.action.manager;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;

import com.hackerspace.dao.BaseTDao;
import com.hackerspace.dao.ProjectShowDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.exception.PageShow;
import com.hackerspace.model.ActionResult;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Project;
import com.hackerspace.model.ProjectShow;
import com.hackerspace.model.User;
import com.hackerspace.util.Log;
import com.hackerspace.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("mystruts")
@Namespace("/manager/projectshow")

public class ProjectShowAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private static Logger log = Log.get(ProjectShowAction.class);
	private BaseTDao<ProjectShow> bDao = new BaseTDao<ProjectShow>();
	private ProjectShowDao psDao = new ProjectShowDao();
	
	private static boolean validation = true;
	
	// field
	public ProjectShow projectShow;

	
	@Action(value="querys", results={
			@Result(name="success", location="/Web/jsp/Manager/Common/Project/ProjectShow.jsp")
	})
	public String queryProjectShows() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Byte status = new Byte((byte) 1);
		try {
			status = Byte.valueOf(hsq.getParameter("status"));
		} catch(NumberFormatException e) {
			// ignore
		}
		
		if (ActionResult.RESULT_SUCCESS.equals((String) hsq.getAttribute("result"))) {
			status = (byte)hsq.getAttribute("status");
		}
		
		PageElem<ProjectShow> pageElem = new PageElem<ProjectShow>();
		pageElem.setCurrentPage(hsq.getParameter("currentPage"));
		pageElem.setPageShow(PageShow.M_PROJECT_SHOWF);
		
		try {
			psDao.queryProjectShows(pageElem, status);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("status", status.toString());
		hsq.setAttribute("pageElem", pageElem);
		
		
		return SUCCESS;
		
	}
	
	@Action(value="create", results={
			@Result(name="success", type="chain", location="querys"),
			@Result(name="input", location="/Web/jsp/Manager/Common/Project/CreProShow.jsp")
	})
	public String createProjectShow() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		HttpSession s = ServletActionContext.getRequest().getSession();
		
		if (StringUtil.isEmptyOrNull(projectShow.getTitle().trim())) {
			hsq.setAttribute("eTitle", "请填写标题");
			validation = false;
		}
		if (StringUtil.isEmptyOrNull(projectShow.getContent().trim())) {
			hsq.setAttribute("eContent", "请填写内容");
			validation = false;
		}
		if (validation == false) {
			validation = true;
			return "input";
		}
		
		User user = (User) s.getAttribute("manager");
		if (user == null) {
			Log.warn(log, "用户未登录, 过滤器问题");
		}
		
		projectShow.setTime(new Timestamp(System.currentTimeMillis()));
		projectShow.setAuthorId(user.getId());
		projectShow.setStatus((byte)0);
		
		try {
			bDao.create(projectShow);
		}  catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", "项目路演创建成功");
		hsq.setAttribute("status", projectShow.getStatus());
		
		return SUCCESS;		
	}
	
	
	@Action(value="updateFir", results={
			@Result(name="success", location="/Web/jsp/Manager/Common/Project/UpProShow.jsp")
	})
	public String updateProjectShowFir() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			hsq.setAttribute("msg", "请求参数错误");
			hsq.setAttribute("url", hsq.getHeader("Referer"));
			return null;
		}
		
		try {
			if (( projectShow = bDao.find(ProjectShow.class, id) ) == null) {
				
			}
				
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		return SUCCESS;	
	}
	
	@Action(value="updateSec", results={
			@Result(name="success", type="chain", location="querys"),
			@Result(name="input", type="chain", location="updateFir")
	})
	public String updateProjectShowSec() throws IOException {
		HttpServletResponse hsr = ServletActionContext.getResponse();
		HttpServletRequest  hsq = ServletActionContext.getRequest();
		HttpSession s = hsq.getSession();
		
		User user = (User) s.getAttribute("manager");
		if (user == null) {
			
		}
		
		if ( StringUtil.isEmptyOrNull(projectShow.getTitle().trim()) ) {
			validation = false;
			hsq.setAttribute("title", "请输入标题");
		}
		if (StringUtil.isEmptyOrNull(projectShow.getContent().trim() )) {
			validation = false;
			hsq.setAttribute("content", "请输入内容");
		}
		if (validation == false) {
			validation = true;
			return "input";
		}
		
		projectShow.setTime( new Timestamp(System.currentTimeMillis()));
		try {
			if (psDao.updateProjectShowContent(projectShow.getId(), projectShow.getContent(), projectShow.getTitle(), projectShow.getTime(), user.getId() ) != 1) {
				
			}
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			return null;
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", "项目路演更新成功");
		hsq.setAttribute("status", (byte)0);
		
		return SUCCESS;
	}
	
	@Action(value="updateSta", results={
			@Result(name="success", type="chain", location="querys")
	})
	public String updateStatus() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		Byte status = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
			status = Byte.valueOf(hsq.getParameter("status"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			hsq.setAttribute("msg", "请求参数错误");
			hsq.setAttribute("url", hsq.getHeader("Referer"));
			return null;
		}
		
		try {
			psDao.updateProjectShowSta(id, status);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			return null;
		}
		
		String msg = null;
		if (status == (byte)0) {
			msg = "项目路演停用成功";
		}
		else {
			msg = "项目路演发布成功";
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", msg);
		hsq.setAttribute("status", (byte)(status^0));
		
		return SUCCESS;
	}

	// 删除
	@Action(value="delete", results={
			@Result(name="success", type="chain", location="querys")
	})
	public String deleteProjectShow() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
		} catch (NumberFormatException e) {
			hsq.setAttribute("url", hsq.getHeader("Referer"));
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		try {
			if (psDao.deleteProjectShow(id) != 1) {
				
			}
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
			return null;
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", "项目路演删除成功");
		hsq.setAttribute("status", (byte)0);
		
		return SUCCESS;
	}
	
	
	
	
	
	public ProjectShow getProjectShow() {
		return projectShow;
	}
	public void setProjectShow(ProjectShow projectShow) {
		this.projectShow = projectShow;
	}
}
