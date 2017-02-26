package com.hackerspace.intercept;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class CharacterIntercept implements Interceptor {

	public HashMap<String, String> hm = new HashMap<String, String>();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	private ServletContext servletContext;
	
	

	public ServletContext getServletContext() {
		return servletContext;
	}


	@Inject
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}


	@Override
	public void init() {
			String filePath =	(String)servletContext.getAttribute("word");
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (null != (line = br.readLine())) {
				String[] strTemp = line.split("=");
				//分别是要替换的字和替换的字
				hm.put(strTemp[0], strTemp[1]);
			}
		} catch (Exception ie) {
			try {
				throw new Exception("读取过滤文件信息出错！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request=(HttpServletRequest)ServletActionContext.getRequest();
		HttpServletResponse response =(HttpServletResponse)ServletActionContext.getResponse();
		/*		StrutsRequestWrapper  request3=(StrutsRequestWrapper) ServletActionContext.getRequest();
		Class c=ServletRequestWrapper.class;
		Field field = c.getDeclaredField("request");
		field.setAccessible(true);
		RequestFacade sr = (RequestFacade) field.get(request3);
		System.out.println(sr.getClass());
		
		Class c2=RequestFacade.class;
		Field field2 = c2.getDeclaredField("request");
		field2.setAccessible(true);
		Request request2 = (Request) (field2.get(sr));
		org.apache.coyote.Request request4=request2.getCoyoteRequest();

		HttpServletResponse response=ServletActionContext.getResponse();*/
//		org.apache.catalina.connector.Request r=(Request)request;
	//	r.getParameter("card");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("我要进行字符过滤了，主要过滤的内容是脏字敏感字和不合规范的页码");
/*		System.out.println(request.getClass());
		request.setCharacterEncoding("UTF-8");
		// 非法字符过滤
		Map map = 	request4.getParameters().paramHashValues;;*/
		//过滤当前页数
	/*	if(map.get("cp")!=null) {
			String[] cp=(String[])map.get("cp");
			System.out.println("当前页数"+cp[0]);
			Pattern p=Pattern.compile("[1-9]*");
			Matcher isNum=p.matcher(cp[0]);
			if(!isNum.matches()) {
			}
		}
		
		
		 //取出过滤的字
		Set<String> keys=hm.keySet();
		
		for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
			String[] s = (String[]) iterator.next();
			for (int i = 0; i < s.length; i++) {
				 //需要过滤的字符
				s[i]=s[i].replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll("\'", "&apos;")
						.replaceAll("\"", "&quot;").replaceAll("a", "1");
				// 过滤不雅字
				Iterator it=keys.iterator();
				while(it.hasNext()) {
					String key=(String)it.next();
					String value=(String) hm.get(key);
					s[i]=s[i].replace(key,value);
				}
			}
		}*/
/*		这是过滤了attribute
 * 		String param="";
		String s="";
		Enumeration params=request.getParameterNames();
		while(params.hasMoreElements()) {
			param=(String)params.nextElement();
			String []values=request.getParameterValues(param);
			for(int i=0;i<values.length;i++) {
				s=values[i];
				s=s.replace("1", "6");
				 values[i]=s;
			}
			request.setAttribute(param, s);
		}*/
		// 放行
		return invocation.invoke();
	}


	private String filtrateString(String content) {
		if (content == null || "".equals(content.trim())) {
			return content;
			}
			content = content.replaceAll("\t", "    ").replaceAll("\r\n", "\n").replaceAll("\n", "<br/>")
					.replaceAll("'", "&#39;").replaceAll("\\\\", "&#92;").replaceAll("\"", "&quot;")
					.replaceAll("a", "1");
			 //取出过滤的字
			Set<String> keys=hm.keySet();
			Iterator<String> it=keys.iterator();
			while(it.hasNext()) {
				String key=it.next();
				String value=hm.get(key);
				content=content.replace(key, value);
			}
			return content;
	}

}
