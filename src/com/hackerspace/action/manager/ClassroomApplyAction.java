package com.hackerspace.action.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import com.hackerspace.dao.BaseTDao;
import com.hackerspace.dao.ClassroomApplyDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.exception.PageShow;
import com.hackerspace.model.ActionResult;
import com.hackerspace.model.ClassroomApply;
import com.hackerspace.model.PageElem;
import com.hackerspace.util.Log;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("mystruts")
@Namespace("/manager/classapply")
public class ClassroomApplyAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private static Logger log = Log.get(ClassroomApplyAction.class);
	
	private List<ClassroomApply> crApplies;
	
	private ClassroomApply craApply;
	
	private BaseTDao<ClassroomApply> bDao = new BaseTDao<ClassroomApply>(log);
	private ClassroomApplyDao craDao = new ClassroomApplyDao(log);
	
	

	/**
	 * 查询全部教室申请信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="querys", results={
			@Result(name="success", location="/Web/jsp/Manager/Public/Classroom/QueryApply.jsp")
	})
	public String queryClassroomApplys() throws IOException {
		HttpServletRequest hsr = ServletActionContext.getRequest();
		HttpServletResponse hsq = ServletActionContext.getResponse();
		
		PageElem<ClassroomApply> pageElem = new PageElem<ClassroomApply>();
		pageElem.setCurrentPage(hsr.getParameter("cp"));
		pageElem.setPageShow(PageShow.M_CLASSROOM_APPLY_SHOW);
		
		try {
			craDao.queryClassroomApplies(pageElem);
		} catch (DaoException e) {
			hsq.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
			
		hsr.setAttribute("pageElem", pageElem);
		
		return SUCCESS;
	}
	
	/**
	 * 查询单个申请信息
	 * @return
	 * @throws IOException
	 */
	@Action(value="query",results={
			@Result(name="success", location="/Web/jsp/Manager/Public/Classroom/ManagerApply.jsp")
	})
	public String queryClassroomApply() throws IOException {
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
			if ( (craApply = bDao.find(ClassroomApply.class, id)) == null) {
				// 未找到的情况下
			}
		}  catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		// 字符串转化
		Integer status = craApply.getStatus();
		String s =  (status == 1 ? "上午" : 
			               (status == 2 ? "下午" :
			            	              (status == 3 ? "晚上" : "一天")) );

		String passed = craApply.isPass() == -1 ? "未处理" :
												(craApply.isPass() == 0 ? "未通过" : "已通过");
		hsq.setAttribute("status", s);
		hsq.setAttribute("passed", passed);
		
		return SUCCESS;
	}
	
	/**
	 * 审批申请是否通过
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update", results={
			@Result(name="success", type="chain",location="querys")
	})
	public String updateStatus() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		Integer isPassed = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
			isPassed = Integer.valueOf(hsq.getParameter("pass"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		try {
			if (craDao.updateStatu(id, isPassed) != 1){
				
			}
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		hsq.setAttribute("result", ActionResult.RESULT_SUCCESS);
		hsq.setAttribute("msg", "审批成功");
		
		return SUCCESS;
	}
	
	
	public void setCraApply(ClassroomApply craApply) {
		this.craApply = craApply;
	}
	public ClassroomApply getCraApply() {
		return craApply;
	}
	public void setCrApplies(List<ClassroomApply> crApplies) {
		this.crApplies = crApplies;
	}
	public List<ClassroomApply> getCrApplies() {
		return crApplies;
	}
}
