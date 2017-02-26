package com.hackerspace.listenter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.action.manager.ProgramAction;
import com.hackerspace.model.Link;
import com.hackerspace.model.Program;
import com.hackerspace.model.Team;
import com.hackerspace.service.ProgramService;
import com.hackerspace.service.TeamService;
import com.hackerspace.util.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;


@WebListener
public class OpenSystem implements ServletContextListener,HttpSessionListener,ServletContextAttributeListener {


    public OpenSystem() {
        
    }


    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }

    

    public void contextInitialized(ServletContextEvent arg0)  {
//    	System.out.println(123);
//         HibernateUtil util = null;
//         System.out.println(HibernateUtil.sessionFactory);

    	// TODO Auto-generated method stub
    
    	
		ProgramService ps=new ProgramService();
	    
	/*	String configPath = "/WEB-INF/word.txt";//filterConfig.getInitParameter("WORD");
		
		String filePath = arg0.getServletContext().getRealPath(configPath);*/
		
		//把路径放入上下文
//		context.put("word", filePath);
		
	    ArrayList<Program> pl=(ArrayList<Program>)ps.getProgram();
	
	    arg0.getServletContext().setAttribute("programList", pl);
//	    arg0.getServletContext().setAttribute("word", filePath);
	    
		/*页尾链接*/
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
		
		TeamService ts = new TeamService();
		List<Team> teams = ts.getTeamView();
		
		arg0.getServletContext().setAttribute("la", la);
		arg0.getServletContext().setAttribute("lb", lb);
		arg0.getServletContext().setAttribute("lc", lc);
		
    }


	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sessionCreated(HttpSessionEvent se) {
	
	}


	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}



    
}
