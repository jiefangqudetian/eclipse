package com.kaishengit.web.account;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;
import com.kaishengit.service.DeptService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String callback1 = "/login?callback=/customer/detail?id=1025&ispublic=true";
		/*String callback = req.getParameter("callback");
		callback = URLDecoder.decode(callback, "UTF-8");
		System.out.println(callback);*/
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies) {
			if("username".equals(cookie.getName())) {
				//cookie存储中文问题
				//String name = cookie.getValue();
				String name = URLDecoder.decode(cookie.getValue(),"UTF-8");
				req.setAttribute("username", name);
			}
		}
		forward("account/login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//三个input框参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		AccountService service = new AccountService();
		DeptService deptService = new DeptService();
		Map<String, Object> res = new HashMap<>();
		try {
			Account account = service.login(username, password);
			String deptName = deptService.findNameByAccount(account);
			account.setDeptName(deptName);
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			
			if (StringUtils.isNoneEmpty(remember)) {
				String name = URLEncoder.encode(username, "UTF-8");
				Cookie cookie = new Cookie("username",name);
				cookie.setDomain("192.168.1.86");
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 30);
				cookie.setHttpOnly(true);
				resp.addCookie(cookie);
			} else {
				Cookie[] cookies = req.getCookies();
				for(Cookie cookie : cookies) {
					if("username".equals(cookie.getName())) {
						cookie.setDomain("localhost");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						resp.addCookie(cookie);
					}
				}
			}
			res.put("state", "success");
			res.put("message", account);
			sendJson(res, resp);
		} catch (Exception e) {
			res.put("state", "error");
			res.put("message", e.getMessage());
			sendJson(res, resp);
		}
	}

}
