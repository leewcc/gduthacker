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
import com.hackerspace.dao.ProjectShowDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.exception.PageShow;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.ProjectShow;
import com.hackerspace.util.Log;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("struts-default")
@Namespace("/user/projectshow")
public class ProjectShowAction extends ActionSupport{
	
	private static Logger log = Log.get(ProjectShowAction.class);
	
	private ProjectShowDao psDao = new ProjectShowDao();
	
	/**
	 * 查询项目路演列表
	 * @return
	 * @throws IOException
	 */
	@Action(value="querys",results={
			@Result(name="success", location="/Web/jsp/View/Public/Project/ProShowList.jsp")
	})
	public String querProjectShows() throws IOException {	
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		PageElem<ProjectShow> pageElem = new PageElem<ProjectShow>();
		pageElem.setCurrentPage(hsq.getParameter("cp"));
		pageElem.setPageShow(PageShow.U_PROJECT_SHOWF);
		
		try {
			psDao.queryProjectShows(pageElem, (byte)1);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		} 
		
		hsq.setAttribute("pageElem", pageElem);
		
		return SUCCESS;
	}
	
	/**
	 * 查询单个项目
	 * @return
	 * @throws IOException 
	 */
	@Action(value="query",results={
			@Result(name="success", location="/Web/jsp/View/Public/Project/ProShowCont.jsp")
	})
	public String queryContent() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			hsq.setAttribute("url", hsq.getHeader("Referer"));
			return null;
		}
		
		ProjectShow ps = null;
		try {
			ps = psDao.queryProjectShow(id);
			
			psDao.updateHitNum(id);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}

		hsq.setAttribute("ps", ps);
			
		return SUCCESS;
	}
}
