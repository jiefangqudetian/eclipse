package com.kaishengit.web.disk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kaishengit.service.DiskService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/disk/delete")
public class DiskDeleteServlet extends BaseServlet{
	
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		service.deleteDiskById(id);
		sendJson(AjaxResult.success(), resp);
	
	}

}
