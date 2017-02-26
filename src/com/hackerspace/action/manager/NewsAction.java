package com.hackerspace.action.manager;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.News;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;
import com.hackerspace.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;

public class NewsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int showNum=15;//分页
	public String editorNews() {
		return SUCCESS;
	}
	/**
	 * 保存新闻
	 * @return
	 */
	public String saveNews() {
		News news=new News();//保存的新闻对象
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//获得user对象
		User u=(User)session.getAttribute("manager");
		//装配News对象
		byte tag=(byte)1;
		try {
			tag=Byte.parseByte(request.getParameter("tag"));
		} catch(Exception e1) {
			tag=1;
		}
		try {
			tag=Byte.parseByte(request.getParameter("tag"));
		} catch(Exception e1) {
			tag=1;
		}
		news.setAuthorId(u.getId());
		news.setContent(request.getParameter("content"));
		news.setIsTop((byte)0);
		news.setTime(new Timestamp(System.currentTimeMillis()));
		news.setTag(tag);
		news.setTitle(request.getParameter("title"));
		String option=request.getParameter("option");
		if(option.equals("1")) {//发布
			news.setStatus((byte)1);
		} else if(option.equals("2")) {//存为草稿
			news.setStatus((byte)0);
		} else {
			return ERROR;
		}
		
		//如果id存在说明是草稿
		NewsService ns=new NewsService();
		String id=request.getParameter("id");
		if(id!=null) {
			news.setId(Integer.parseInt(id));
			if(ns.updateNews(news)) {
				request.setAttribute("message","保存成功");
				return SUCCESS;
			}
		} else {
			if(ns.saveNews(news)) {
				request.setAttribute("message","保存成功");
				return SUCCESS;
			}
		}
		return ERROR;
	}
	public String prepareEditorNews() {
		return SUCCESS;
	}
	
	/**
	 * 展示已发布的新闻
	 * @return
	 */
	public String showPublishedNews () {
		HttpServletRequest request=ServletActionContext.getRequest();
		String tag=request.getParameter("tag");
		String cp=request.getParameter("cp");
		if(tag==null||"".equals(tag)) {
			tag="0";
		}
		if(cp==null) {
			cp="1";
		}
		NewsService ns=new NewsService();
		
		PageElem<News> pe=new PageElem<News>();
		pe.setPageShow(showNum);//每页展示的条数
		pe.setCurrentPage(Integer.parseInt(cp));
 		ns.findNews((byte)1, Byte.parseByte(tag),pe);
 		request.setAttribute("tag", tag);
 		request.setAttribute("cp",cp);
		request.setAttribute("pageElem", pe);
 		return SUCCESS;
	}
	/**
	 * 草稿箱
	 */
	public String showDrafts() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String cp=request.getParameter("cp");
		if(cp==null||"".equals(cp)) {
			cp="1";
		}
		NewsService ns=new NewsService();
		
		PageElem<News> pe=new PageElem<News>();
		pe.setPageShow(showNum);//每页展示的条数
		pe.setCurrentPage(Integer.parseInt(cp));

 		ns.findDrafts((byte)0,pe);
		
 		request.setAttribute("pageElem", pe);
 		return SUCCESS;
	}
	/**
	 * 删除新闻
	 */
	public String deleteOneNews() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		NewsService ns=new NewsService();
		boolean flag=ns.deletePublishedNews(Integer.parseInt(id));
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
	 * 置顶
	 * @return
	 */
	public String takeToTop() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString =request.getParameter("id");
		if(idString==null||"".equals(idString)) return ERROR;
		int id=Integer.parseInt(idString);
		byte top=Byte.parseByte(request.getParameter("top"));
		
		NewsService ns=new NewsService();
		
 		if(ns.takeToTop(id,top)) {
 			return SUCCESS;
 		}
 		return ERROR;
	}
	
	public String showOneNews() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString =request.getParameter("id");
		if(idString==null||idString.equals("")) return ERROR;
		int id=Integer.parseInt(idString);
		NewsService ns=new NewsService();
		News news= ns.getOneArticle(id);
		request.setAttribute("news", news);
		System.out.println(request.getServletPath());
		if(request.getParameter("status").equals("1")) {
			return "published";
		} else {
			return "drafts";
		}
		
		
	}
	
	public String showViewNews() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString =request.getParameter("id");
		if(idString==null||idString.equals("")) return ERROR;
		int id=Integer.parseInt(idString);
		NewsService ns=new NewsService();
		News news= ns.getOneArticle(id);
		request.setAttribute("news", news);
		return "success";
	}
}

