package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AdminService;

public class LoginServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		String userName = "";
		for (Cookie cookie : cookies) {
			if ("username".equals(cookie.getName())) {
				userName = cookie.getValue();
				break;
			}
		}
		req.setAttribute("username", userName);
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("username");
		String pass = req.getParameter("password");
		String callback = req.getParameter("callback");
		String rememberme = req.getParameter("rememberme");
		AdminService adminService = new AdminService();
		try {
			//判断账号密码，如果错误，catch住异常，直接返回登陆页面
			
			Admin admin = adminService.login(userName,pass);
			//登录成功，将admin对象放入session中，防止直接通过url访问其他页面，跳过login
			HttpSession session = req.getSession();
			session.setAttribute("admin", admin);
			//判断记住密码框是否选中，如果选中remember值不为null，未选中时删除cookie
			if (StringUtils.isNotEmpty(rememberme)) {
				Cookie cookie = new Cookie("username", userName);
				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				cookie.setHttpOnly(true);
				
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				for (Cookie cookie : cookies) {
					if ("username".equals(cookie.getName())) {
						cookie.setDomain("localhost");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						resp.addCookie(cookie);
						break;
					}
				}
			}
			
			
			if (StringUtils.isNotEmpty(callback)) {
				resp.sendRedirect(callback);
			} else {
				resp.sendRedirect("/list");
			}
			
		} catch (ServiceException e) {
			// TODO: handle exception
			req.setAttribute("message", e.getMessage());
			req.setAttribute("username", userName);
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
	}
	

}
