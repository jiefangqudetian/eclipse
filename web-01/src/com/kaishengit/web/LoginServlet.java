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
			//�ж��˺����룬�������catchס�쳣��ֱ�ӷ��ص�½ҳ��
			
			Admin admin = adminService.login(userName,pass);
			//��¼�ɹ�����admin�������session�У���ֱֹ��ͨ��url��������ҳ�棬����login
			HttpSession session = req.getSession();
			session.setAttribute("admin", admin);
			//�жϼ�ס������Ƿ�ѡ�У����ѡ��rememberֵ��Ϊnull��δѡ��ʱɾ��cookie
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
