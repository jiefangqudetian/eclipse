package com.kaishengit.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.web.BaseServlet;

@WebServlet("/customer/my/trans")
public class MyCustomerTransServlet extends BaseServlet {
	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		String toAccountId = req.getParameter("toAccountId");
		int accountId = getCurrAccount(req).getId();
		try {
			service.transByCustId(custId, accountId, toAccountId);
			resp.sendRedirect("/customer/list");
		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		} catch (ForbiddenException e) {
			resp.sendError(403, e.getMessage());
		}

	}

}
