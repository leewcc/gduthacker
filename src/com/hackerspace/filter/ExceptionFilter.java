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


@WebFilter("/*")
public class ExceptionFilter implements Filter {

  
    public ExceptionFilter() {
       
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			chain.doFilter(request, response);
		}catch(Exception e){
			e.printStackTrace();
			HttpServletRequest req = (HttpServletRequest)request;
			req.setAttribute("message", "很抱歉，操作过程出现了错误");
			String url = req.getHeader("Referer");
			req.setAttribute("url", url.substring(url.indexOf("/GDUT")));
			req.getRequestDispatcher("/Web/jsp/Result/Jump.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
