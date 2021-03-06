package com.kaishengit.web.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.AccountService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/account/delete")
public class AccountDeleteServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AccountService service = new AccountService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accId = req.getParameter("accId");
		
		try {
			service.deleteAccount(accId);
			AjaxResult result = AjaxResult.success();
			sendJson(result, resp);
			
		} catch (Exception e) {
			AjaxResult result = AjaxResult.error(e.getMessage());
			System.out.println(e.getMessage());
			sendJson(result, resp);
		}
	}

}
