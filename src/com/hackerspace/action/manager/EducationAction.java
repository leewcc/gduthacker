package com.hackerspace.action.manager;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;
import org.hibernate.cache.infinispan.util.FlagAdapter;
import org.hibernate.type.TrueFalseType;
import org.slf4j.Logger;

import com.hackerspace.dao.BaseTDao;
import com.hackerspace.dao.EducationDao;
import com.hackerspace.dao.ProjectShowDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.Cooperation;
import com.hackerspace.model.Education;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.ProjectShow;
import com.hackerspace.model.User;
import com.hackerspace.service.CooperationService;
import com.hackerspace.service.EducationService;
import com.hackerspace.util.Log;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("mystruts")
@Namespace("/manager/education")

//@Namespace("/Web/jsp/Manager/Common/Education")
public class EducationAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private final int showNum=15;//分页
	private static Logger log = Log.get();
	
	private BaseTDao<Education> bDao = new BaseTDao<Education>(log);
	private EducationDao eDao = new EducationDao(log);
	
	
	private Education education;
	
	public Education getEducation() {
		return education;
	}
	
	public void setEducation(Education education) {
		this.education = education;
	}
	
	/**
	 * 创建讲座管理 和 实践实训
	 * @return
	 * @throws IOException 
	 */
	@Action(value="create", results={
			@Result(name="success", location="/Web/jsp/Result/Success.jsp")
	})
	public String createEducation() throws IOException {
		HttpServletResponse hsr = ServletActionContext.getResponse();
		HttpSession s = ServletActionContext.getRequest().getSession();
		
		User u = (User)s.getAttribute("manager");
		
		education.setStatus((byte)1);
		education.setTime(new Timestamp(new Date().getTime()));
		education.setAuthorId(u.getId());
		
		Log.info(log, "createEducation()参数列表:authorId={};content={};status={};tag={};time={};title={}",
				1, education.getContent(),education.getStatus(),education.getTag(),education.getTime(),education.getTitle());
		
		try {
			bDao.create(education);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		return SUCCESS;
	}
	
	
	
	@Action(value="query", results={
			@Result(name="queryF", location="/Web/jsp/Manager/Common/Education/QueryF.jsp"),
			@Result(name="queryS", location="/Web/jsp/Manager/Common/Education/QueryS.jsp"),
			@Result(name="error", location="/Web/jsp/Result/Error.jsp")})
	public String queryEducations() {
		
		Log.info(log, "queryEducations()进入");
		HttpServletRequest hsq = ServletActionContext.getRequest();
		
		
		byte tag = (byte)1;
		byte status = (byte)1;
		try {
			 tag = Byte.valueOf(hsq.getParameter("tag"));
		} catch (Exception e) {
			tag = (byte)1; 
		}
		
		try {
			status = Byte.valueOf(hsq.getParameter("status"));
		} catch (Exception e) {
			status = (byte)1;
		}
		
		PageElem<Education> pageElem = new PageElem<Education>();
		
		pageElem.setCurrentPage(hsq.getParameter("currentPage"));
		pageElem.setPageShow(10);
		
		Log.info(log, "queryEducations()参数:tag={};status={};currentPage={};pageShow={}", tag,status,pageElem.getCurrentPage(),pageElem.getPageShow());
		
		
		if ( eDao.queryEducations(pageElem, tag, status) == true) {
			
			hsq.setAttribute("pageElem", pageElem);
			hsq.setAttribute("status", ""+status);
			hsq.setAttribute("tag", tag);
			
			Log.info(log, "queryEducations()操作成功");
			if (tag == (byte)0)
				return "queryF";
			else 
				return "queryS";
		}
		
		Log.info(log, "queryEducations()操作失败");
		return ERROR;
	}
	
	@Action(value="updateF", results={
			@Result(name="updateF", location="/Web/jsp/Manager/Common/Education/UpdateF.jsp"),
			@Result(name="updateS", location="/Web/jsp/Manager/Common/Education/UpdateS.jsp")
	})
	public String updateEduFir() {
		
		Log.info(log, "updateEduFir()方法进入");
		HttpServletRequest hsq = ServletActionContext.getRequest();
		
		boolean flag = true;
		
		Integer id = null;
		String sId = null;
		Byte tag = null;
		try {
			sId = hsq.getParameter("id");
			id = Integer.valueOf(sId);
			tag = Byte.valueOf(hsq.getParameter("tag"));
		} catch (Exception e) {
			Log.info(log, "updateEduFir()参数:id错误={};tag={}",sId, tag);
			flag = false;
		}
		
		if (flag == false) {
			Log.info(log, "updateEduFir()操作失败");
			return ERROR;
		}
		
		Log.info(log, "updateEduFir()参数:id={}",sId);
		
		try {
			if ((education = eDao.queryEducation(id) ) == null) {
				
			}
				
		} catch (DaoException e) {
			
		}
		
		if (tag == (byte)0) 
			return "updateF";
		else 
			return "updateS";
	}
	
	// 内容修改
	
	@Action(value="updateS", results={
			@Result(name="success", location="/Web/jsp/Result/Success.jsp"),
			@Result(name="error", location="/Web/jsp/Result/Error.jsp")
	})
	public String updateEduSec() {
		
		Log.info(log, "updateEduSec()进入");
			
		
		Timestamp time = new Timestamp(new Date().getTime());
		education.setTime(time);
		
		Log.info(log, "updateEduFir()参数:id={};title={};content={};time={};authorId={};tag={};status={}", 
				education.getId(),education.getTitle(),education.getContent(),education.getTime(),education.getAuthorId(),
				education.getTag(),education.getStatus());
		
		// TODO 
		
		
		return ERROR;
		
	}
	
	
	@Action(value="updateSta", results={
			@Result(name="success", location="/Web/jsp/Result/Success.jsp"),
			@Result(name="error", location="/Web/jsp/Result/Error.jsp")
	})
	public String updateEduSta() {
		
		Log.info(log, "updateEduSta()方法进入");
		
		HttpServletRequest hsq = ServletActionContext.getRequest();
		
		Integer id = null;
		Byte status = null;
		String sId = hsq.getParameter("id");
		String sStatus = hsq.getParameter("status");
		
		try {
			id = Integer.valueOf(sId);
			status = Byte.valueOf(sStatus);
		} catch (Exception e) {
			Log.info(log, "updateEduSta()参数错误: id={};status={}", sId, sStatus);
			return ERROR;
		}
		
		Log.info(log, "updateEduSta()参数: id={};status={}", sId, sStatus);
		
		if (eDao.updateEduSta(id, status) == 1 ) {
			
			Log.info(log, "updateEduSta()操作成功");
			return SUCCESS;
		}
		
		Log.info(log, "updateEduSta()操作失败");
		return ERROR;
	
	}
	
	@Action(value="delete", results={
			@Result(name="success", location="/Web/jsp/Result/Success.jsp"),
			@Result(name="error", location="/Web/jsp/Result/Error.jsp")
	})
	
	public String deleteEdu() {
		Log.info(log, "deleteEdu()方法进入");
		
		HttpServletRequest hsq = ServletActionContext.getRequest();
		
		Integer id = null;
		String sId = hsq.getParameter("id");
		try {
			id = Integer.valueOf(sId);
			
		} catch (Exception e) {
			Log.info(log, "updateEduSta()参数错误: id={}", sId);
			return ERROR;
		}
		
		Log.info(log, "updateEduSta()参数: id={}", sId);
		
		if (eDao.deleteEducation(id) == true) {
			
			Log.info(log, "deleteEdu()操作成功");
			return SUCCESS;
		}
		
		Log.info(log, "deleteEdu()操作失败");
		return ERROR;
	}
	
	
	/**
	 * @author CHEN
	 * 教育中的信息编辑
	 */
	public String editorCooperation() {
		return SUCCESS;
	}
	/**
	 * 保存合作
	 * @return
	 */
	public String saveEducation() {
		Education e=new Education();//保存的合作对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//获得user对象
		User u=(User)session.getAttribute("manager");
		//装配Education对象
		e.setAuthorId(u.getId());
		e.setContent(request.getParameter("content"));
		byte tag = (byte)1;
		byte status = (byte)1;
		
		try {
			tag=Byte.parseByte(request.getParameter("tag"));
		} catch(Exception e1) {
			tag=1;
		}
		
		try {
			status=Byte.parseByte(request.getParameter("status"));
		} catch(Exception e1) {
			status=1;
		}
		e.setTag(tag);
		e.setTime(new Timestamp(System.currentTimeMillis()));
		e.setTitle(request.getParameter("title"));
		String option=request.getParameter("option");
		request.setAttribute("status", "1");
		if(option.equals("1")) {//发布
			e.setStatus((byte)1);
		} else if(option.equals("2")) {//存为草稿
			e.setStatus((byte)0);
		} else {
			return ERROR;
		}
		
		//如果id存在说明是草稿
		EducationService ns=new EducationService();
		String id=request.getParameter("id");
		if(id!=null) {
			e.setId(Integer.parseInt(id));
			if(ns.updateEducation(e)) {
				request.setAttribute("message", "保存成功");
				return SUCCESS;
			}
		} else {
			if(ns.saveEducation(e)) {
				request.setAttribute("message", "保存成功");
				return SUCCESS;
			}
		}
		return ERROR;
	}
	public String prepareEditorEducation() {
		return SUCCESS;
	}
	
	/**
	 * 展示已发布的合作
	 * @return
	 */
	public String showPublishedEducation () {
		HttpServletRequest request=ServletActionContext.getRequest();
		String tag=request.getParameter("tag");
		String cp=request.getParameter("cp");
		String status=request.getParameter("status");
		if(tag==null) {
			tag="0";
		}
		if(cp==null) {
			cp="1";
		}
		EducationService ns=new EducationService();
		
		PageElem<Education> pe=new PageElem<Education>();
		pe.setPageShow(showNum);//每页展示的条数
		
		pe.setCurrentPage(Integer.parseInt(cp));
 		ns.findEducation((byte)1, Byte.parseByte(tag),pe);
 		request.setAttribute("status",status);
 		request.setAttribute("tag", tag);
		request.setAttribute("pageElem", pe);
 		return SUCCESS;
	}
	/**
	 * 草稿箱
	 */
	public String showDrafts() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String cp=request.getParameter("cp");
		String status=request.getParameter("status");
		if(cp==null) {
			cp="1";
		}
		EducationService ns=new EducationService();
		PageElem<Education> pe=new PageElem<Education>();
		pe.setPageShow(showNum);//每页展示的条数
		pe.setCurrentPage(Integer.parseInt(cp));

 		ns.findDrafts((byte)0,pe);
		
 		request.setAttribute("status", status);
 		request.setAttribute("pageElem", pe);
 		return SUCCESS;
	}
	/**
	 * 删除合作
	 */
	public String deleteOneEducation() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		EducationService ns=new EducationService();
		boolean flag=ns.deletePublishedEducation(Integer.parseInt(id));
		String status=request.getParameter("status");
		
		if(status.equals("0")&&flag) {
			request.setAttribute("message", "删除成功");
			return "drafts";
		} else if(status.equals("1")&&flag){
			request.setAttribute("message", "删除成功");
			return  "published";
		} else if(status.equals("0")&&!flag) {
			request.setAttribute("message", "删除失败");
			return "drafts";
		} else if(status.equals("1")&&!flag) {
			request.setAttribute("message", "删除失败");
			return "drafts";
		} else {
			return "error";
		}
	}


	/**
	 * 显示一篇合作
	 * @return
	 */
	public String showOneEducation() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString =request.getParameter("id");
		if(idString==null||idString.equals("")) return ERROR;
		int id=Integer.parseInt(idString);
		EducationService ns=new EducationService();
		Education c= ns.getOneArticle(id);
		request.setAttribute("education", c);
		if(request.getParameter("status").equals("0")) {
			return "drafts";
		} else {
			return "published";
		}
	}
	
	/**
	 * 在前台显示合作
	 */
	public String showViewEducation() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString =request.getParameter("id");
		if(idString==null||idString.equals("")) return ERROR;
		int id=Integer.parseInt(idString);
		EducationService ns=new EducationService();
		Education c= ns.getOneArticle(id);
		request.setAttribute("education", c);
			return "success";
	}
}
