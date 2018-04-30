package com.kaishengit.web.chart;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.SaleService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/charts/process/count")
public class ChartsProcessCountServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SaleService service = new SaleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int accountId = getCurrAccount(req).getId();
		List<Map<String, Object>> mapList = service.countSaleProcess(accountId);
		AjaxResult ajaxResult = AjaxResult.success(mapList);
		sendJson(ajaxResult, resp);
	}

}
