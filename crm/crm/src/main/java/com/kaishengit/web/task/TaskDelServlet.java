package com.kaishengit.web.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TaskService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/task/del")
public class TaskDelServlet extends BaseServlet{
	TaskService taskService = new TaskService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(123);
		String taskId = req.getParameter("taskId");
		int accountId = getCurrAccount(req).getId();
		try {
			taskService.delById(taskId,accountId);
			resp.sendRedirect("/task/list");
		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		} catch (ForbiddenException e) {
			resp.sendError(403, e.getMessage());
		}
	}

}
