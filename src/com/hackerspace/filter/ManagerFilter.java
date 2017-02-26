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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.util.logging.resources.logging;

import com.hackerspace.dao.UserDao;
import com.hackerspace.model.User;
import com.hackerspace.util.CookieUtils;


@WebFilter("/Web/jsp/Manager/*")
public class ManagerFilter implements Filter {
	private CookieUtils cookieUtils=new CookieUtils();
	private UserDao userDAO =new UserDao();


    public ManagerFilter() {
        
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpServletResponse res = (HttpServletResponse)response;
		String url = req.getRequestURI();
		System.out.println(url);
	
		
		if(url.contains("Manager/PublicPart"))
			chain.doFilter(request, response);
		else{
			HttpSession ses = req.getSession();
			User user = (User)ses.getAttribute("manager");
			if(user != null)
				chain.doFilter(request, response);
			else{
				//加入session中没有该用户那就尝试从cookie中获得
//				try {
//					ses.setAttribute("manager", cookieUtils.getCookie(req, userDAO));
//					if(ses.getAttribute("manager")!=null) {
//						chain.doFilter(request, response);
//					}
//					return;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				req.setAttribute("message", "请先登录");
				req.setAttribute("url", "/GDUTHackerSpace/Web/jsp/Manager/PublicPart/ManagerLogin.jsp");
				req.getRequestDispatcher("/Web/jsp/Result/Jump.jsp").forward(request, response);
			}
			
		}
			
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
