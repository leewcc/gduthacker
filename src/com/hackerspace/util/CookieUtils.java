package com.hackerspace.util;


import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.hackerspace.action.manager.ManagerAction;
import com.hackerspace.dao.UserDao;
import com.hackerspace.model.User;
import com.opensymphony.xwork2.ActionContext;


/**
 * cookie的增加、删除、查询
 */
public class CookieUtils {
	public static final String SessionID = "SessionID";

	// 添加一个cookie
	public Cookie addCookie(User user) {
		Cookie cookie = new Cookie(SessionID, user.getCard() + ","
				+user.getPassword());
		System.out.println("添加"+cookie);
		cookie.setMaxAge(60 * 60 * 24 * 7);// cookie保存一周
		cookie.setPath("/");
		cookie.setHttpOnly(true);//防止脚本攻击
		return cookie;
	}

	// 得到cookie
	public User getCookie(HttpServletRequest request, UserDao userDAO) throws Exception {
		Cookie[] cookies = request.getCookies();
		System.out.println("cookies: " + cookies);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println("cookie: " + cookie.getName());
				if (CookieUtils.SessionID.equals(cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split(",");
						String card = split[0];
						String password = split[1];
						User user = userDAO.login(card, password);
						return user;
					}
				}
			}
		}
	
		return null;
	}

	// 删除cookie
	public Cookie delCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (SessionID.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					cookie.setPath("/");
					return cookie;
				}
			}
		}
		return null;
	}
}
