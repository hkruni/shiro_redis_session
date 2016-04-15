package com.cmdi.yjs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.misc.BASE64Decoder;

import com.cmdi.yjs.model.User;
import com.cmdi.yjs.service.UserService;
import com.cmdi.yjs.util.MD5Util;
import com.cmdi.yjs.util.SpringContextHolder;

public class SessionFilter extends HttpServlet implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5459569249359940473L;

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		System.out.println("此时的session id : " + session.getId());
		User user = (User)session.getAttribute("user");
		if(user == null) {
			Cookie loginInfoCookie = null;
			Cookie []cookies = request.getCookies();
			if(cookies == null || cookies.length == 0) {//如果是首次登录，cookies会是null
				System.out.println("没有cookie");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return;
			}
			for(Cookie cookie : cookies) {
				if("loginInfo".equals(cookie.getName())) {
					loginInfoCookie = cookie;
				}
			}
			if(loginInfoCookie != null) {
				String checkStr = loginInfoCookie.getValue();
				String username = new String(new BASE64Decoder().decodeBuffer(checkStr.split("\\_")[0]));
				String password = checkStr.split("\\_")[1];
				UserService userService = (UserService)SpringContextHolder.getBean("userService");
				User u = userService.getUser(username);
				if(u != null) {
					System.out.println("cookie中找到了user");
					if(password.equals(MD5Util.md5(u.getPassword()))) {
						session.setAttribute("user", u);
					}
				}
			}
		}
		chain.doFilter(request, response);
	}
	
	
}
