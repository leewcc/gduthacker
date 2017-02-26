package com.hackerspace.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hackerspace.model.User;
import com.hackerspace.service.MessageService;


@WebFilter("/*")
public class MessageFilter implements Filter {


    public MessageFilter() {
        
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession ses = req.getSession();
		
		User u = (User)ses.getAttribute("user");
		if(u != null) {
			try{
				MessageService ms = new MessageService();
				req.setAttribute("unread", ms.getUnReadMess(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
