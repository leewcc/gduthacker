package com.hackerspace.intercept;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ActionIntercept extends AbstractInterceptor{
    private static final long serialVersionUID = 1L; 
    private static Set<String> set = new HashSet<>();
    static{
    	set.add("Maker");
    	set.add("SeeMaker");
    	set.add("UserLogin");
    	set.add("ManagerLogin");
    	set.add("InputBase");
    	set.add("InputCardPic");
    	set.add("InputPicture");
    	set.add("InputSecurities");
    	set.add("showViewNews");
    	set.add("showPublishedNews");
    	set.add("showViewCooperation");
    	set.add("showPublishedEducation");
    	set.add("showPublishedCooperation");
    	set.add("showProgram");
    	set.add("showLink");
    	set.add("showHomePage");
    	set.add("showViewEducation");  	
    	set.add("SelectQuestionView");
    	set.add("showViewEducation"); 
    	set.add("prepareUserPicture");
    	set.add("uploadUserPicture");
    	set.add("/user/project");
    	set.add("/user/projectshow");
    	set.add("Code");
    }
    
    public void destroy() { 
 
        System.out.println("Destory"); 
    } 
 
    public void init() { 
 
        System.out.println("Init"); 
    } 
 
   
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("action执行之前"); 
        String name = invocation.getInvocationContext().getName(); 
        System.out.println("请求方法：" + name); 
        ActionContext ac = invocation.getInvocationContext(); 
        Map<String, Object> session = ac.getSession(); 
        boolean allow = set.contains(name); 
        if (allow) { 
            // 如果用户想登陆，或者执行的是allow方法则不拦截，使之通过 
            // invocation.invoke()继续运行拦截器后续的处理 
 
            return invocation.invoke(); 
 
        } else { 
        	HttpServletRequest req = ServletActionContext.getRequest();
        	String url = req.getRequestURI();
        	System.out.println(url);
        	
        	// 天信部分
        	if (url.contains("/user/project") || url.contains("/user/projectshow")) {
        		return invocation.invoke();
        	}
        	
        	if(url.contains("/Manager") || url.contains("/manager")){
        		
        		User u = (User)session.get("manager");
        		if(u == null){
        			req.setAttribute("message", "请先登录");
        			req.setAttribute("url", "/GDUTHackerSpace/Web/jsp/Manager/PublicPart/ManagerLogin.jsp");
        			return "jump";
        		}else{
        			return invocation.invoke();
        		}
        		
        	}else if(url.contains("/View/User") || url.contains("/user")){
        		User u = (User)session.get("user");
        		if(u == null){
        			req.setAttribute("message", "请先登录");
        			req.setAttribute("url", "/GDUTHackerSpace/Web/jsp/View/Public/User/Login.jsp");
        			return "jump";
        		}else{
        			return invocation.invoke();
        		}
        		
        	}else{
        		return "404";
        	}
        	
        	
        }
	} 

}
