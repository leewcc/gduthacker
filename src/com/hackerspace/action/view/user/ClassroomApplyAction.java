package com.hackerspace.action.view.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.annotations.Check;
import org.slf4j.Logger;

import com.hackerspace.dao.BaseTDao;
import com.hackerspace.dao.ClassroomApplyDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.Classroom;
import com.hackerspace.model.ClassroomApply;
import com.hackerspace.model.User;
import com.hackerspace.util.Log;
import com.hackerspace.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("mystruts")
@Namespace("/user/classapply")
public class ClassroomApplyAction extends ActionSupport{
	
	private static Logger log = Log.get(ClassroomApplyAction.class);
	
	private List<Classroom> classrooms;
	
	private List<ClassroomApply> classroomApplies;
	
	private Classroom classroom;
	
	private ClassroomApply classroomApply;

	private BaseTDao<ClassroomApply> bDao = new BaseTDao<ClassroomApply>(log);
	
	private ClassroomApplyDao craDao = new ClassroomApplyDao(log);
	
	private static boolean validation = true;  
	
	private static String message = null;
	
	@Action(value="check", results={
			@Result(name="success", location="/Web/jsp/View/User/Services/ApplyForClass/ChooseClassrom.jsp"),
			@Result(name="error", location="/Web/jsp/Result/Jump.jsp")
	})
	public String Check() {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		User user = (User) hsq.
				getSession().getAttribute("user");
		if (user == null) {
			hsq.setAttribute("message", "请登陆后再去申请");
			hsq.setAttribute("url", "/GDUTHackerSpace/Web/jsp/View/User/Login.jsp");
			return ERROR;
		}
		
		
		return SUCCESS;
	}
	
	@Action(value="query", results={
			@Result(name="success", location="/Web/jsp/View/User/Services/ApplyForClass/ChooseClassrom.jsp")
	})
	public String queryClass() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		String date = hsq.getParameter("date");
		String status = hsq.getParameter("status");
		if (!StringUtil.isNumeric(status)) {
			hsr.sendError(HttpServletResponse.SC_BAD_GATEWAY);
			return null;
		}
		
		try {
			classrooms = craDao.queryClassroomAll();
			
			classroomApplies = craDao.queryClassroomApply(Integer.valueOf(status), date );
			
			//第三步：	移除已被申请的课室
			for(ClassroomApply c : classroomApplies) {
				classrooms.remove(c.getClassroom());
			}
		
		} catch (DaoException e) {
			e.printStackTrace();
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("classrooms", classrooms);
		hsq.setAttribute("status", status);
		hsq.setAttribute("date", date);
		
		if (message != null) {
			hsq.setAttribute("msg", message);
			message = null;
		}
		
		return SUCCESS;
	}
	
	
	@Action(value="apply", results={
			@Result(name="success", type="chain", location="query"),
			@Result(name="input", location="/Web/jsp/View/User/Services/ApplyForClass/ApplyClassroom.jsp")
	})
	public String insertClassApply() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		if (classroomApply == null) {
			Log.warn(log, "classroom apply :classroom is null");
			return "input";
		}
		
		String roomId = hsq.getParameter("roomId");
		String date = hsq.getParameter("date");
		String status = hsq.getParameter("status");
		
		if (StringUtil.isEmptyOrNull(classroomApply.getTeam()) ){
			hsq.setAttribute("eTeam", "请填写申请单位");
			validation = false;
		}
		if (StringUtil.isEmptyOrNull(classroomApply.getUser())){
			hsq.setAttribute("eUser", "请填写申请人");
			validation = false;
		}
		if (!StringUtil.isNumeric(classroomApply.getContact())){
			hsq.setAttribute("eContact", "请填写申请人电话");
			validation = false;
		}
		if (StringUtil.isEmptyOrNull(classroomApply.getReason())){
			hsq.setAttribute("eReason", "请填写申请理由");
			validation = false;
		}
		if (validation == false) {
			validation = true;
			hsq.setAttribute("status", status);
			hsq.setAttribute("id", roomId);
			hsq.setAttribute("date", date);
			hsq.setAttribute("num", hsq.getParameter("classroomNum"));
			return "input";
		}
		
		
		Classroom cr = new Classroom();
		try {
			cr.setId(Integer.valueOf(roomId));
			classroomApply.setClassroom(cr);
			classroomApply.setDate(date);
			classroomApply.setStatus(status);
			classroomApply.setPostTime(new Timestamp(System.currentTimeMillis()));
			classroomApply.setClassroomIsOpen((byte)1);
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} catch (ParseException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		Log.debug(log, "insertClassApply()参数列表: user={}, contact={}, postTime={}, status={}, date={}, reason={}, team={}, id={}", 
				classroomApply.getUser(),classroomApply.getContact(), classroomApply.getPostTime(),
				classroomApply.getStatus(), classroomApply.getDate(), classroomApply.getReason(), classroomApply.getTeam(), 
				classroomApply.getClassroom().getId());
		
		try {
			craDao.create(classroomApply) ;
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		message = "申请成功";
		
 		return SUCCESS;
	}
	
	
	
	
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	public Classroom getClassroom() {
		return classroom;
	}
	
	
	public void setClassroomApply(ClassroomApply classroomApply) {
		this.classroomApply = classroomApply;
	}
	
	public ClassroomApply getClassroomApply() {
		return classroomApply;
	}
}
