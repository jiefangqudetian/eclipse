package com.kaishengit.web.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.web.BaseServlet;

@WebServlet("/account/list")
public class AccountListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	public  AccountListServlet() {
		System.out.println("用户页");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		forward("account/list",req,resp);
		
	}
	
}
