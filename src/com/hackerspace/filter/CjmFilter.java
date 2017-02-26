package com.hackerspace.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.User;

/**
 * 该过滤器是属于俊铭用的
 * Servlet Filter implementation class CjmFilter
 */
//@WebFilter("/*")
public class CjmFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CjmFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		System.out.println("如果你看到了这个信息，说明你受到了俊铭过滤器的入侵，文件在过滤器那个包里，自己关掉");
		User user =new User();
		user.setId(1);
		user.setName("张三");
		user.setCard("3214006241");
		user.setPassword("123456");
		user.setPicture("images/column/1\f00e2a04-2821-488c-aab7-d6d205d099e4.jpg");
		HttpSession session =ServletActionContext.getRequest().getSession();
		session.setAttribute("user", user);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
