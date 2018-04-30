package com.kaishengit.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;

@WebServlet("/customer/list")
public class CustomerListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	CustomerService service = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//这个p从何获取，指的是页码么，首次加载没有，其他点分页时就有？是的
		String p = req.getParameter("p");
		String isPublic = req.getParameter("ispublic");
		int pageNo = 1;
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		int accountId;
		//公海客户accountId赋值0，非公海客户accountId赋值当前id， 公海客户增加标识字段addurl
		if(StringUtils.isNotEmpty(isPublic) && isPublic.equals("true")){
			accountId = Config.PUBLIC_ID;
			Page<Customer> page = service.findMyCustomerByPage(accountId,pageNo);
			req.setAttribute("page", page);
			req.setAttribute("name", "公海");
			req.setAttribute("addurl", "ispublic=true");
			req.setAttribute("detail", "public");
			forward("customer/list", req, resp);
		} else {
			accountId = getCurrAccount(req).getId();
			Page<Customer> page = service.findMyCustomerByPage(accountId,pageNo);
			req.setAttribute("page", page);
			req.setAttribute("name", "我的");
			req.setAttribute("detail", "my");
			forward("customer/list", req, resp);
		}
	}
}
