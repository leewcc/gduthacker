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

import com.hackerspace.model.User;


//@WebFilter("/*")
public class PowerFilter implements Filter {


    public PowerFilter() {
       
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//第一步：	获取请求的url
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpServletResponse res = (HttpServletResponse)response;
		String url = req.getRequestURI();
		
		//第二步：	判断请求的是否为公共url
		if(url.contains("Public")){
				chain.doFilter(request, response);
				return;
		}
		
		//第三步：	请求非公共页面时，获取session，获取用户信息
		HttpSession session = req.getSession(true);
		User user = (User)session.getAttribute("user");
				
		//第四步：	判断是否有用户，无用户则跳转到登陆页面
		if(user == null){
			res.sendRedirect("");
			return;
		}
		
		//第五步：	获取用户角色
		int role = user.getRole();
		
		//第六步：	判断请求的是否为普通用户
		if(url.contains("View/Common") && role >= 1)
			chain.doFilter(request, response);
		
		//第七步：	判断请求的是否为高级用户
		else if(url.contains("View/High") && role >= 2)
			chain.doFilter(request, response);
		
		//第八步：	判断请求的是否为老师
		else if(url.contains("View/Teacher") && role >= 3)
			chain.doFilter(request, response);
		
		//第九步：	判断请求的是否为录入员
		else if(url.contains("Manager/Common") && role >= 4)
			chain.doFilter(request, response);
		
		else if(url.contains("Manager/High") && role >= 5)
			chain.doFilter(request, response);
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
