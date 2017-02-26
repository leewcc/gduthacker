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
import com.hackerspace.dao.UserDao;
import com.hackerspace.model.User;
import com.hackerspace.util.CookieUtils;



@WebFilter("/Web/jsp/View/*")
public class UserFilter implements Filter {


    public UserFilter() {
       
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpServletResponse res = (HttpServletResponse)response;
		String url = req.getRequestURI();
		System.out.println(url);
		
		
		if(url.contains("View/Public"))
			chain.doFilter(request, response);
		else{
			HttpSession ses = req.getSession();
			
//			if(url.contains("View/User/Public/User")){
//				String role = req.getParameter("role");
//				if("manager".equals(role)) {
//					User u = (User)ses.getAttribute("manager");
//					if(u == null) {
//						try {
//							CookieUtils cookieUtils =new CookieUtils();
//							UserDao userDAO =new UserDao();
//							ses.setAttribute("manager", cookieUtils.getCookie(req, userDAO));
//							if(ses.getAttribute("manager")!=null) {
//								chain.doFilter(req, res);
//							}
//							return;
//						} catch (Exception e) {
//								e.printStackTrace();
//						}
//						req.setAttribute("message", "请先登录");
//						req.setAttribute("url", "/GDUTHackerSpace/Web/jsp/Manager/PublicPart/ManagerLogin.jsp");
//						req.getRequestDispatcher("/Web/jsp/Result/Jump.jsp").forward(request, response);
//						return;
//					}else{
//						chain.doFilter(request, response);
//						return;
//					}
//					
//				}
//			}
			
			User user = (User)ses.getAttribute("user");
			if(user != null){
				chain.doFilter(request, response);
			}else{
				try {
					CookieUtils cookieUtils =new CookieUtils();
					UserDao userDAO =new UserDao();
					ses.setAttribute("user", cookieUtils.getCookie(req, userDAO));
					if(ses.getAttribute("user")!=null) {						
						chain.doFilter(req, res);
					}
					return;
				} catch (Exception e) {
						e.printStackTrace();
				}
				req.setAttribute("message", "请先登录");
				req.setAttribute("url", "/GDUTHackerSpace/Web/jsp/View/Public/User/Login.jsp");
				req.getRequestDispatcher("/Web/jsp/Result/Jump.jsp").forward(request, response);
			}
			
		}
	}

	
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
