package com.hackerspace.action.manager;

import java.io.IOException;
import java.util.List;
import com.hackerspace.model.Classroom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import com.hackerspace.dao.BaseTDao;
import com.hackerspace.dao.ClassroomDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.ActionResult;
import com.hackerspace.model.ClassroomApply;
import com.hackerspace.util.Log;
import com.hackerspace.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("mystruts")
@Namespace("/manager/classroom")

public class ClassroomAction extends ActionSupport{
	
	private static Logger log = Log.get(ClassroomAction.class);
	
	private BaseTDao<ClassroomApply> bDao = new BaseTDao<ClassroomApply>();
	private ClassroomDao crDao = new ClassroomDao();
	
	private ClassroomApply classroomApply;
	private Classroom classroom;
	private List<Classroom> classrooms = null;
	
	private static boolean validationC = false;    // 参数是否经过 create方法
	private static boolean validationD = false;    // 参数是否经过 delete方法
	private static boolean validationE = false;    // 参数验证是否正确 create;
	
	@Action(value="create", results={
			@Result(name="success", type="redirect", location="query"),
			@Result(name="input", type="redirect", location="query")
	})
	public String insertClassroom() throws IOException {	
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		BaseTDao<Classroom> bcDao = new BaseTDao<Classroom>(log);
		if(StringUtil.isEmptyOrNull(classroom.getNum().trim())) {
			validationE = true;   // true 则会 set message
			return "input";
		}
		
		// 默认设置为 "";
		if (classroom.getPosition() == null) {
			classroom.setPosition("");
		}
		// 默认开放
		classroom.setClassroomIsOpen(true);
		
		try {
			bcDao.create(classroom);
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		validationC = true;

		return SUCCESS;
	}
	

	
	/**
	 * 管理所以已创建课室
	 * @return
	 * @throws IOException
	 */
	@Action(value="query", results={     
			@Result(name="success", location="/Web/jsp/Manager/Common/Services/ApplyForClass/ManagerClassroom.jsp")
	})
	public String queryClassroom() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		try {
			classrooms = crDao.selectClassroom() ;
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		if (validationC == true) {
			validationC = false;
			hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
			hsq.setAttribute("msg", "课室创建成功");
		}
		if (validationD == true) {
			validationD = false;
			hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
			hsq.setAttribute("msg", "课室删除成功");
		}
		if (validationE == true) {
			validationE = false;
			hsq.setAttribute("eNum", "请填写课室号");
		}

		hsq.setAttribute("classrooms", classrooms);
		
		return SUCCESS;
	}
	
	@Action(value="delete", results={
			@Result(name="success", type="redirect", location="query")
	})
	public String deleteClassroom() throws IOException {
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
			if (crDao.deleteClassroom(id) != 1) {
				// 
			}
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		validationD = true;
		
		return SUCCESS;
	}
	
	
	
	public void setClassroomApply(ClassroomApply classroomApply) {
		this.classroomApply = classroomApply;
	}
	
	public ClassroomApply getClassroomApply() {
		return classroomApply;
	}
	
	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	public List<Classroom> getClassrooms() {
		return classrooms;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	public Classroom getClassroom() {
		return classroom;
	}


//	@Action(value="apply", results={
//	@Result(name="success", type="chain", location="query")
//})
//public String insertClassApply() {
//Log.info(log, "classroomApply()进入");
//
//
//classroomApply.setPostTime(new Timestamp(System.currentTimeMillis()));
//
//	 try {
//	bDao.create(classroomApply);
//} catch (DaoException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//	 
//	hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
//	hsq.setAttribute("msg", );
//	
//	return SUCCESS;
//	
//
//
}



