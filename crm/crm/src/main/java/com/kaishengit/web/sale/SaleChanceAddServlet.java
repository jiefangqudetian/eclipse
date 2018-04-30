package com.kaishengit.web.sale;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.SaleChance;
import com.kaishengit.service.SaleService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
/**
 * @author LENOVO
 *添加销售机会页，成功前端跳转销售机会列表页
 */
@WebServlet("/sale/add")
public class SaleChanceAddServlet extends BaseServlet{

	SaleService saleService = new SaleService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//从session中获得account对象的id
		int accountId = getCurrAccount(req).getId();
		//从配置文件中获取process，用于前端页面显示销售进度
		List<String> process = saleService.findAllProcess();
		//根据accountId从数据库中获取customer集合，用于前端页面显示关联客户列表
		List<Customer> customers = saleService.findAllCustomersByAccountId(accountId);
		req.setAttribute("process", process);
		req.setAttribute("customerList", customers);
		forward("sale/add", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saleName = req.getParameter("salename");
		String custId = req.getParameter("custId");
		String worth = req.getParameter("worth");
		String process = req.getParameter("process");
		String content = req.getParameter("content");
		
		int accountId = getCurrAccount(req).getId();
		//根据表单提交数据创建saleChance对象
		SaleChance saleChance = new SaleChance(saleName, Integer.parseInt(custId), Float.parseFloat(worth), process, content, accountId);
		//将saleChance对象保存到数据库
		saleService.saveSaleChance(saleChance);
		//返回成功信息
		AjaxResult result = AjaxResult.success();
		sendJson(result,resp);
	}

}
