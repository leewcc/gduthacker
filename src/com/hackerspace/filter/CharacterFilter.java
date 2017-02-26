package com.hackerspace.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.catalina.connector.Request;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;



/**
 * @author CHEN
 * 描述：过滤字符
 * 该过滤已经作废 但是有研究的作用 请勿删
 * Servlet Filter implementation class CharacterFilter
 */
//@WebFilter(
//		urlPatterns={"/*"},
//		filterName="/CharacterFilter",
//		initParams={
//		@WebInitParam(name="WORD",value="/WEB-INF/word.txt")
//		})
public class CharacterFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CharacterFilter() {
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
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("我要进行字符过滤了，主要过滤的内容是脏字敏感字和不合规范的页码");
		request.setCharacterEncoding("UTF-8");
		// 非法字符过滤
		HttpServletRequest myRequest =  (HttpServletRequest)request;
		Request realRequest =(Request)myRequest;
		
		String url=myRequest.getRequestURI();
		Map map = realRequest.getParameterMap();
		
		
		
		//过滤当前页数
//		if(map.get("cp")!=null) {
//			String[] cp=(String[])map.get("cp");
//			System.out.println("当前页数"+cp[0]);
//			Pattern p=Pattern.compile("[1-9]*");
//			Matcher isNum=p.matcher(cp[0]);
//			if(!isNum.matches()) {
//				cp[0]="1";
//			}
//		}
		Collection coll = map.values();
		// 取出过滤的字
		Set<String> keys=hm.keySet();
		
		for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
			String[] s = (String[]) iterator.next();
			for (int i = 0; i < s.length; i++) {
				// 需要过滤的字符
				s[i]=s[i].replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll("\'", "&apos;")
						.replaceAll("\"", "&quot;");
				// 过滤不雅字
				Iterator it=keys.iterator();
				while(it.hasNext()) {
					String key=(String)it.next();
					String value=(String) hm.get(key);
					s[i]=s[i].replace(key,value);
				}
			}
		}
		// 放行
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	public HashMap<String, String> hm = new HashMap<String, String>();

	public void init(FilterConfig filterConfig) throws ServletException {
		
		
		String configPath = "/WEB-INF/word.txt";//filterConfig.getInitParameter("WORD");
																																		
		ServletContext sc = filterConfig.getServletContext();
		String filePath = sc.getRealPath(configPath);
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			String line;
			while (null != (line = br.readLine())) {
				String[] strTemp = line.split("=");
				//分别是要替换的字和替换的字
				hm.put(strTemp[0], strTemp[1]);
			}
		} catch (IOException ie) {
			throw new ServletException("读取过滤文件信息出错！");
		}

	}

}
