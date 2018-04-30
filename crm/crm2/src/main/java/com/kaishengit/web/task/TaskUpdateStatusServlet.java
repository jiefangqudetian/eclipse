package com.kaishengit.web.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.TaskService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/task/status")
public class TaskUpdateStatusServlet extends BaseServlet{
	TaskService taskService = new TaskService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		String status = req.getParameter("status");
		taskService.updateTaskStatusById(taskId,status);
		sendJson(AjaxResult.success(), resp);
	}

}
