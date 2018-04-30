package com.kaishengit.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;

/**
 * post
 * 进：customer/edit.jsp  post向数据库更新顾客数据   出：edit.jsp 返回result对象只带状态信息success
 * get
 * 进：客户端      出：customer/edit.jsp 带值customer对象  客户来源source集合  行业trades集合用于跳转edit.jsp页面后数据回显
 */
@WebServlet("/customer/edit")
public class CustomerEditServlet extends BaseServlet {
	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("id");
		
		try {
			Customer cust = service.getCustomerById(custId);
			//来源 行业列表  下拉框方便编辑
			List<String> sources = service.findAllSources();
			List<String> trades = service.findAllTrades();
			
			req.setAttribute("sources", sources);
			req.setAttribute("trades", trades);
			req.setAttribute("customer", cust);
			forward("customer/edit",req,resp);
		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		} catch (ForbiddenException e) {
			resp.sendError(403, e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		String custname = req.getParameter("custname");
		String sex = req.getParameter("sex");
		String jobtitle = req.getParameter("jobtitle");
		String address = req.getParameter("address");
		String mobile = req.getParameter("mobile");
		String source = req.getParameter("source");
		String trade = req.getParameter("trade");
		String level = req.getParameter("level");
		String mark = req.getParameter("mark");
		
		
		service.edit(custId,custname,sex,jobtitle,address,mobile,source,trade,level,mark);
		
		AjaxResult result = AjaxResult.success();
		sendJson(result, resp);
	}
	
}
