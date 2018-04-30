package com.kaishengit.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Account;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.SaleChance;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SaleService;
import com.kaishengit.util.Config;
import com.kaishengit.web.BaseServlet;
@WebServlet("/customer/detail")
public class CustomerDetailServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerService();
	AccountService accountService = new AccountService();
	SaleService saleService = new SaleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("id");
		//检验是否带公海链接  如果带了走公海详情页
		if (req.getParameter("ispublic") != null) {
			try {
				
				Customer cust = customerService.findCustomerById(custId, Config.PUBLIC_ID);
				List<Account> accList = accountService.findAllAccount();
				// 把当前客户的销售机会列表显示在页面的跟进机会模块中
				List<SaleChance> chanceList = saleService.findSaleChanceListByCustId(cust.getId());
				req.setAttribute("chanceList", chanceList);//用于显示
				req.setAttribute("customer", cust);//用于显示
				req.setAttribute("accountList", accList);//用于认领
				forward("customer/publicdetail", req, resp);
			} catch (ServiceException e) {
				resp.sendError(404,e.getMessage());
			} catch (ForbiddenException e) {
				resp.sendError(403,e.getMessage());
			}
			//未带公海链接走我的详情页
		} else {
			try {
				int accountId = getCurrAccount(req).getId();
				Customer cust = customerService.findCustomerById(custId,accountId);
				List<Account> accList = accountService.findAllAccount();
				// 把当前客户的销售机会列表显示在页面的跟进机会模块中
				List<SaleChance> chanceList = saleService.findSaleChanceListByCustId(cust.getId());
				req.setAttribute("customer", cust);//用于显示
				req.setAttribute("accountList", accList);//用于转交
				req.setAttribute("chanceList", chanceList);//用于显示
				forward("customer/mydetail", req, resp);
			} catch (ServiceException e) {
				resp.sendError(404,e.getMessage());
			} catch (ForbiddenException e) {
				resp.sendError(403,e.getMessage());
			}
		}
	}

}
