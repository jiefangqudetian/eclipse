package com.kaishengit.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.web.BaseServlet;

/**
 * 进:mydetail.jsp  出:mylist.jsp 
 * 客户详情页删除客户，跳转客户列表页
 */
@WebServlet("/customer/del")
public class CustomerDelServlet extends BaseServlet {
	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;

	
	
	/* 
	 * 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		Customer customer = service.getCustomerById(custId);
		int accountId = customer.getAccountId();
		try {
			service.delCustById(custId);
			if (accountId==0) {
				resp.sendRedirect("/customer/list?ispublic=true");
			} else {
				resp.sendRedirect("/customer/list");
			}
		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		} catch (ForbiddenException e) {
			resp.sendError(403, e.getMessage());
		}

	}

}
