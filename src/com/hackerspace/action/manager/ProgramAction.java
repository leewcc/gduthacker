package com.hackerspace.action.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.HaskerSystem;
import com.hackerspace.model.Link;
import com.hackerspace.model.Program;
import com.hackerspace.service.ProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class ProgramAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 展示所有的栏目
	 * @return
	 */
	public String showProgram() {
		HttpServletRequest request=ServletActionContext.getRequest();
		ProgramService ps=new ProgramService();
		List<Program> l=(ArrayList<Program>)ps.getProgram();
		request.setAttribute("programList", l);
		return SUCCESS;
	}
	/**
	 * 管理所有的栏目
	 */
	public String managerProgram() {
		HttpServletRequest request=ServletActionContext.getRequest();
		ProgramService ps=new ProgramService();
		List<Program> l=(ArrayList<Program>) ps.getManagerProgram();
		request.setAttribute("programList",l);
		return SUCCESS;
	}
	/**
	 * 更新栏目
	 * @return
	 */
	public String saveProgram() {
		//获得参数
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			int status=Integer.parseInt(request.getParameter("status"));
			String name=request.getParameter("name");
			Program p=new Program();
			p.setId(id);
			p.setName(name);
			p.setStatus(status);
			ProgramService ps=new ProgramService();
			if(ps.saveProgram(p)) {
			    ArrayList<Program> l=(ArrayList<Program>)ps.getProgram();
			    ServletActionContext.getServletContext().setAttribute("programList", l);
				return SUCCESS;
			}
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String updateSystemName() {
		HttpServletRequest request=ServletActionContext.getRequest();
		ProgramService ps=new ProgramService();
		HaskerSystem name=ps.getSystemName();
		if(name.getName()==null) {
			name.setName("暂无系统名");
		}
		request.setAttribute("HackerSystem", name);
		return SUCCESS;
	}
	
	public String saveSystemName() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String name=request.getParameter("systemName");
		int id=Integer.parseInt(request.getParameter("id"));
		HaskerSystem hs=new HaskerSystem();
		hs.setId(id);
		hs.setName(name);
		ProgramService ps=new ProgramService();
		ps.saveSystemName(hs);
		return SUCCESS;
	}

	
	public String managerLink() {
		HttpServletRequest request=ServletActionContext.getRequest();
		ProgramService ps=new ProgramService();
		List<Link> l=ps.getLists();
		List<Link> la=new ArrayList<Link>();
		List<Link> lb=new ArrayList<Link>();
		List<Link> lc=new ArrayList<Link>();
		for(Link ll:l) {
			if(ll.getBelong()==1) {
				la.add(ll);
			} else if(ll.getBelong()==2) {
				lb.add(ll);
				
			} else if(ll.getBelong()==3) {
				lc.add(ll);
			}
		}
		request.setAttribute("la", la);
		request.setAttribute("lb", lb);
		request.setAttribute("lc", lc);
		return SUCCESS;
	}
	
	public String updateLink(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String [] ids =request.getParameterValues("id");
		String [] names =request.getParameterValues("name");
		String [] belongs =request.getParameterValues("belong");
		String [] links =request.getParameterValues("link");
		List<Link> la=new ArrayList<Link>();
		List<Link> lb=new ArrayList<Link>();
		for(int i=0;i<ids.length;i++) {
			Link ll=new Link();
			ll.setId(Integer.parseInt(ids[i]));
			ll.setBelong(Integer.parseInt(belongs[i]));
			ll.setName(names[i]);
			ll.setLink(links[i]);
			if(!"0".equals(ids[i])) la.add(ll);//更新
			else lb.add(ll);//保存
		}
		ProgramService ps=new ProgramService();
		ps.updateList(la,lb);
		
		List<Link> l=ps.getLists();
		List<Link> laa=new ArrayList<Link>();
		List<Link> lbb=new ArrayList<Link>();
		List<Link> lcc=new ArrayList<Link>();
		for(Link ll:l) {
			if(ll.getBelong()==1) {
				laa.add(ll);
			} else if(ll.getBelong()==2) {
				lbb.add(ll);
				
			} else if(ll.getBelong()==3) {
				lcc.add(ll);
			}
		}
		ServletActionContext.getServletContext().setAttribute("la", laa);
		ServletActionContext.getServletContext().setAttribute("lb", lbb);
		ServletActionContext.getServletContext().setAttribute("lc", lcc);
		
		return SUCCESS;
	}
	
	public String deleteLink() {
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		ProgramService ps=new ProgramService();
		ps.deleteLink(id);
		
		List<Link> l=ps.getLists();
		List<Link> laa=new ArrayList<Link>();
		List<Link> lbb=new ArrayList<Link>();
		List<Link> lcc=new ArrayList<Link>();
		for(Link ll:l) {
			if(ll.getBelong()==1) {
				laa.add(ll);
			} else if(ll.getBelong()==2) {
				lbb.add(ll);
				
			} else if(ll.getBelong()==3) {
				lcc.add(ll);
			}
		}
		ServletActionContext.getServletContext().setAttribute("la", laa);
		ServletActionContext.getServletContext().setAttribute("lb", lbb);
		ServletActionContext.getServletContext().setAttribute("lc", lcc);
		return SUCCESS;
	}
	/**
	 * 移动位置
	 * @return
	 */
	public String moveProgram() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String isUp=request.getParameter("isUp");
		String id=request.getParameter("id");
		String hid=request.getParameter("hid");//上一个id的值
		String fid=request.getParameter("fid");//下一个id的值
		ProgramService ps=new ProgramService();
		if("1".equals(isUp)) {
			ps.moveProgram(hid,id);
		} else if("0".equals(isUp)) {
			ps.moveProgram(id,fid);
		} else {
			return ERROR;
		}
	    ArrayList<Program> l=(ArrayList<Program>)ps.getProgram();
	
	    ServletActionContext.getServletContext().setAttribute("programList", l);
	    
		return SUCCESS;
	}
	/**
	 * 设置可不可见
	 * @return
	 */
	public String operationProgram() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String idString=request.getParameter("id");
		String statusString =request.getParameter("status");
		if(idString !=null) {
			int id=Integer.parseInt(idString);
			int status=Integer.parseInt(statusString);
			ProgramService ps=new ProgramService();
			if(ps.operationProgram(id,status)) {
			    ArrayList<Program> l=(ArrayList<Program>)ps.getProgram();
				
			    ServletActionContext.getServletContext().setAttribute("programList", l);
				return SUCCESS;
			}
		}
		return ERROR;
	}
}
