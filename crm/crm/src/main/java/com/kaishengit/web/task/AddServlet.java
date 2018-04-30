package com.kaishengit.web.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/task/addd")
public class AddServlet extends BaseServlet{
	TaskService service = new TaskService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("123");
		String name = req.getParameter("name");
		System.out.println(name);
		String sex = (String)req.getAttribute("sex");
		System.out.println(sex);
		forward("task/add2", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskName = req.getParameter("taskName");
		String finishTime = req.getParameter("finishTime");
		System.out.println(taskName);
		System.out.println(finishTime);
		req.setAttribute("name", "tom");
		forward("task/list2", req, resp);
	}

}
