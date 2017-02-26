package com.hackerspace.intercept;

import java.util.Map;

import com.hackerspace.model.Message;
import com.hackerspace.model.User;
import com.hackerspace.service.MessageService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MessageIntercept extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		MessageService ms = new MessageService();
		ActionContext ac = ActionContext.getContext();
		Map req = (Map)ac.get("request");
		Map ses = ac.getSession();
		
		User u = (User)ses.get("user");
		if(u != null){
			req.put("unread", ms.getUnReadMess(u));
		}
		return invocation.invoke();
		
	}

}
