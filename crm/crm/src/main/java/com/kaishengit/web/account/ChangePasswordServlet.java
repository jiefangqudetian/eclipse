package com.kaishengit.web.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/account/changePassword")
public class ChangePasswordServlet extends BaseServlet{
	AccountService accountService = new AccountService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("account/changePassword", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oldPassword = req.getParameter("oldpassword");
		String newPassword = req.getParameter("newpassword");
		String confirm = req.getParameter("confirm");
		int accountId = getCurrAccount(req).getId();
		
		try {
			accountService.changePassword(accountId,oldPassword,newPassword,confirm);
			sendJson(AjaxResult.success(), resp);
		} catch (ServiceException e) {
			sendJson(AjaxResult.error(e.getMessage()), resp);
		}
	}

}
