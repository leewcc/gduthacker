package com.hackerspace.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.Messager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

/**
 * 类说明：HTML特殊字符过滤器
 * 
 * @author CHEN
 */
public class HTMLCharacterFilter implements Filter {

	public HashMap<String, String> hm = new HashMap<String, String>();
	
	public void init(FilterConfig filterConfig) throws ServletException {
		String filePath =	(String)filterConfig.getServletContext().getRealPath("/WEB-INF/word.txt");
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

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		request=new HTMLCharacterRequest(request);
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
	// html特殊字符处理类
	class HTMLCharacterRequest extends HttpServletRequestWrapper {

		public HTMLCharacterRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			return filter(name,super.getParameter(name));
		}
		@Override
		public String[] getParameterValues(String name) {
			String[] values = super.getParameterValues(name);
			if (values == null || values.length == 0)
				return values;
			for (int i = 0; i < values.length; i++) {
				String str = values[i];
				values[i] = filter(name,str);
			}
			return values;
		}
		/**
		 * 对特殊的html字符进行编码
		 * 
		 * @param message
		 * @return
		 */
		private String filter(String name,String message) {
			if(message==null) {
				return null;
			}
			if("cp".equals(name)||"tp".equals(name)) {//处理页数
				
				//过滤非法页码
				Pattern p=Pattern.compile("^[1-9]\\d*$");
				Matcher isNum=p.matcher(message);
				if(!isNum.matches()) {
					message= message.replace(message,"1");
				}
			} else {
				message=message.replaceAll("\n", "<br/>");
				//过滤敏感字
				Set<String> keys=hm.keySet();
				Iterator<String> it=keys.iterator();
				while(it.hasNext()) {
					String key=it.next();
					String value=hm.get(key);
					message=message.replace(key, value);
				} 
			}
			return (message.toString());

		}
	}
}


