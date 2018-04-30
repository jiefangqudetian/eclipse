package com.kaishengit.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.CustomerService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;

@WebServlet("/customer/my/public")
public class MyCustomerPublicServlet extends BaseServlet {
	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("id");
		int accountId = getCurrAccount(req).getId();
		
		service.toPublicByCustId(custId, accountId);
		sendJson(AjaxResult.success(), resp);

	}

}
