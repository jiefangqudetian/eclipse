package com.kaishengit.web.account;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.web.BaseServlet;

/**
 * @author LENOVO
 *安全退出调到此页面,将session销毁后,
 */
@WebServlet("/account/logout")
public class LogoutServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 强制销毁
		req.getSession().invalidate();
		
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if ("username".equals(cookie.getName())) {
				String name = URLDecoder.decode(cookie.getValue(),"UTF-8");
				req.setAttribute("username", name);
			}
		}
		forward("account/login", req, resp);
	}

}
