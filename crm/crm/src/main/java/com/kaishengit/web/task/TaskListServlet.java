package com.kaishengit.web.task;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Task;
import com.kaishengit.service.TaskService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/task/list")
public class TaskListServlet extends BaseServlet{
	TaskService taskService = new TaskService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//如果不登录会报空指针异常
		String show = req.getParameter("show");
		int accountId = getCurrAccount(req).getId();
		
		List<Task> taskList = taskService.findTaskListByAccountId(accountId,show);
		req.setAttribute("taskList", taskList);
		forward("task/list",req,resp);
	}

}
