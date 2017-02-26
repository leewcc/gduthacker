package com.hackerspace.action.manager;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.Session;
import org.hibernate.sql.HSQLCaseFragment;
import org.slf4j.Logger;

import com.hackerspace.dao.BaseTDao;
import com.hackerspace.dao.ProjectDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.exception.PageShow;
import com.hackerspace.model.ActionResult;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Project;
import com.hackerspace.model.Team;
import com.hackerspace.model.User;
import com.hackerspace.util.Log;
import com.hackerspace.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.finder.ClassFinder.Info;
import com.sun.istack.internal.FinalArrayList;

/**
 * 管理员项目管理action
 * @author tianx
 */
@ParentPackage("mystruts")
@Namespace("/manager/project")

public class ProjectAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Log.get(Project.class);
	
	private ProjectDao pDao = new ProjectDao();
	private BaseTDao<Project> bDao = new BaseTDao<Project>(log);
	
	public Project project;
	public List<Team> teams;
	
	private static boolean validation = true;    // 参数验证; 默认正确
	
	public void setProject(Project project) {
		this.project = project;
	}
	public Project getProject() {
		return project;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public List<Team> getTeams() {
		return teams;
	}
	
	

	/**
	 * 创建project的第一步: 查询现有团队(未删除的)
	 * @return
	 * @throws IOException 
	 */
	@Action(value="befcre", results={
		@Result(name="success", location="/Web/jsp/Manager/Common/Project/CreateProject.jsp")
	})
	public String queryTeams() throws IOException {
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		if (validation == false) {
			validation = true;
		}
		try {
			teams = pDao.queryTeams();
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		return SUCCESS;
	}
	
	@Action(value="create", results={
			@Result(name="success", type="chain",location="query"),
			@Result(name="input", type="chain", location="befcre")
	})
	public String createProject() throws IOException { 
		//TODO 缺少作者名字
		Log.debug(log, "createProject(),参数列表  "
				+ "titile:{};content:{};tag:{};teamId:{};时间:{}", 
				project.getTitle(), project.getContent(),
				project.getTag(), project.getId(),new Timestamp(new Date().getTime()));
		
		HttpServletRequest hsq =  ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		HttpSession s = ServletActionContext.getRequest().getSession();
		
		if (StringUtil.isEmptyOrNull(project.getTitle().trim())) {
			hsq.setAttribute("eTitle", "请填写标题");
			validation = false;
		}
		if (StringUtil.isEmptyOrNull(project.getContent().trim())) {
			hsq.setAttribute("eContent", "请填写内容");
			validation = false;
		}
		if (project.getTeamId() < 1) {
			hsq.setAttribute("eTeam", "请选择团队");
			validation = false;
		}
		if (validation == false) {
			// 静态类 static 与线程问题
			validation = true;
			return "input";
		}
		
		User user = (User) s.getAttribute("manager");
		project.setStatus((byte) 0);
		project.setTime(new Timestamp(new Date().getTime()));
		project.setAuthorId(user.getId());
		
		
		try {
			bDao.create(project);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", "项目创建成功");
		hsq.setAttribute("status", project.getStatus());
		
		return SUCCESS;	
	}
	
	
	@Action(value="query", results={
			@Result(name="success", location="/Web/jsp/Manager/Common/Project/CheckProject.jsp")
	})
	public String queryProject() throws IOException {
		
		HttpServletRequest  hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		PageElem<Project> pageElem = new PageElem<Project>();
		pageElem.setCurrentPage(hsq.getParameter("currentPage"));
		pageElem.setPageShow(PageShow.M_PROJECT_SHOW);
		
		// 获取前台传来的请求参数
		// 0-未发布; 1-已发布
		byte status = (byte)1;
		byte tag = (byte)0;
		try {
			status = Byte.valueOf(hsq.getParameter("status"));
			tag = Byte.valueOf(hsq.getParameter("tag"));
		} catch (NumberFormatException e) {
			// 设置默认值
		}
		if (ActionResult.RESULT_SUCCESS.equals((String) hsq.getAttribute("result"))) {
			byte b = (byte)hsq.getAttribute("status");
			status = b;
		}
		
		
		try {
			pDao.queryProject(pageElem, status, tag);
		} catch (final DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("pageElem", pageElem);
		hsq.setAttribute("status", String.valueOf(status));
		hsq.setAttribute("tag", String.valueOf(tag));
		return SUCCESS;
	}
	
	
	
	@Action(value="updateFir",results={
			@Result(name="success", location="/Web/jsp/Manager/Common/Project/UpPro.jsp")
	})
	public String updateProjectFir() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
		} catch(NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		try {
			project = bDao.find(Project.class, id);
				
			teams = new BaseTDao<Team>().list("select new Team(id, name) from Team");
		} catch (final DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		return SUCCESS;
	}
	
	
	@Action(value="updateSta", results={
			@Result(name="success", type="chain", location="query")
	})
	public String updateSta() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		Byte status = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
			status = Byte.valueOf(hsq.getParameter("status"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		
		try {
			// 数据库中该id不存在
			if (pDao.updateProjectSta(id, status) != 1) {
				hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
		} catch (final DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		String msg = null;
		if (status == (byte)0) {
			msg = "项目停用成功";
		}
		else {
			msg = "项目发布成功";
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", msg);
		hsq.setAttribute("status", (byte)(status^0));
		
		return SUCCESS;
	}
	
	
	@Action(value="delete",  results={
			@Result(name="success", type="chain", location="query")
	})
	public String deleteProject() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		
		try {
			if ( pDao.deleteProject(id) != 1 ) {
				hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
		} catch (final DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", "项目删除成功");
		hsq.setAttribute("status", (byte)0);
		return SUCCESS;
	}
	
	
	@Action(value="updateSec", results={
			@Result(name="success", type="chain",location="query"),
			@Result(name="input", type="chain", location="updateFir")
		})
	public String updateProjectSec() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Byte tag = null;
		try {
			tag = Byte.valueOf(hsq.getParameter("tag"));
			project.setTag(tag);
		} catch (NumberFormatException e) {
			validation = false;
		} catch (NullPointerException e) {
			validation = false;
		}
	
		Log.debug(log, "updateProjectSec()进入 ;"
				+ " 参数 id:{};title:{}; teamId:{}; tag:{}; content:{};time:{}", 
				project.getId(),project.getTitle(),project.getTeamId(),project.getTag(),project.getContent(),
				new Timestamp(System.currentTimeMillis()));
		
		if (StringUtil.isEmptyOrNull(project.getTitle().trim() )) {
			validation = false;
			hsq.setAttribute("title","标题不能为空");
		}
		if (StringUtil.isEmptyOrNull(project.getContent().trim())) {
			validation = false;
			hsq.setAttribute("title","内容不能为空");
		}
		if (validation == false) {
			validation = true;
			return "input";
		}
		
		// TODO 自动装载的时候就应该有一个异常处理的
		project.setTime(new Timestamp(System.currentTimeMillis()));
		try {
			bDao.update(project);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", "项目更新成功");
		hsq.setAttribute("status", project.getStatus());
		
		return SUCCESS;
	}
}
