package com.hackerspace.action.manager;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.Cooperation;
import com.hackerspace.model.News;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;
import com.hackerspace.service.CooperationService;
import com.hackerspace.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;

public class CooperationAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int showNum=15;
	public String editorCooperation() {
		return SUCCESS;
	}
	/**
	 * 保存合作
	 * @return
	 */
	public String saveCooperation() {
		Cooperation c=new Cooperation();//保存的合作对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//获得user对象
		User u=(User)session.getAttribute("manager");
		//装配Cooperation对象
		c.setAuthor(u);
		c.setDes(request.getParameter("content"));
		c.setDate(new Timestamp(System.currentTimeMillis()));
		c.setTag(Byte.parseByte(request.getParameter("tag")));
		c.setName(request.getParameter("title"));
		c.setUrl(request.getParameter("url"));
		String option=request.getParameter("option");
		request.setAttribute("status", "1");
		if(option.equals("1")) {//发布
			c.setStatus((byte)1);
		} else if(option.equals("2")) {//存为草稿
			c.setStatus((byte)0);
		} else {
			request.setAttribute("message", "保存失败");
			return ERROR;
		}
		
		//如果id存在说明是草稿
		CooperationService ns=new CooperationService();
		String id=request.getParameter("id");
		if(id!=null) {
			c.setId(Integer.parseInt(id));
			if(ns.updateCooperation(c)) {
				request.setAttribute("message", "保存成功");
				return SUCCESS;
			}
		} else {
			if(ns.saveCooperation(c)) {
				request.setAttribute("message", "保存成功");
				return SUCCESS;
			}
		}
		return ERROR;
	}
	public String prepareEditorCooperation() {
		return SUCCESS;
	}
	
	/**
	 * 展示已发布的合作
	 * @return
	 */
	public String showPublishedCooperation () {
		HttpServletRequest request=ServletActionContext.getRequest();
		String tag=request.getParameter("tag");
		String cp=request.getParameter("cp");
		String status=request.getParameter("status");
		if(tag==null||"".equals(tag)) {
			tag="0";
		}
		if(cp==null) {
			cp="1";
		}
		CooperationService ns=new CooperationService();
		
		PageElem<Cooperation> pe=new PageElem<Cooperation>();
		pe.setPageShow(showNum);//每页展示的条数
		pe.setCurrentPage(Integer.parseInt(cp));
 		ns.findCooperation((byte)1, Byte.parseByte(tag),pe);
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
		try {
			int i=Integer.parseInt(cp);
		} catch(Exception e) {
			cp="1";
		}
		CooperationService ns=new CooperationService();
		PageElem<Cooperation> pe=new PageElem<Cooperation>();
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
	public String deleteOneCooperation() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		CooperationService ns=new CooperationService();
		boolean flag=ns.deletePublishedCooperation(Integer.parseInt(id));
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
	public String showOneCooperation() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString =request.getParameter("id");
		if(idString==null||idString.equals("")) return ERROR;
		int id=Integer.parseInt(idString);
		CooperationService ns=new CooperationService();
		Cooperation c= ns.getOneArticle(id);
		request.setAttribute("cooperation", c);
		if(request.getParameter("status").equals("0")) {
			return "drafts";
		} else {
			return "published";
		}
	}
	
	/**
	 * 在前台显示合作
	 */
	public String showViewCooperation() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString =request.getParameter("id");
		if(idString==null||idString.equals("")) return ERROR;
		int id=Integer.parseInt(idString);
		CooperationService ns=new CooperationService();
		Cooperation c= ns.getOneArticle(id);
		request.setAttribute("cooperation", c);
			return "success";
	}
}
