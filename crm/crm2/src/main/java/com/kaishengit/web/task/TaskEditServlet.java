package com.kaishengit.web.task;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Task;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TaskService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/task/edit")
public class TaskEditServlet extends BaseServlet{
	TaskService service = new TaskService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		Task task = service.findTaskById(taskId);
		
		String taskName = task.getTitle();
		String finishTime = new SimpleDateFormat("yyyy-MM-dd ").format(task.getFinishTime());
		req.setAttribute("taskName", taskName);
		req.setAttribute("finishTime", finishTime);
		req.setAttribute("taskId", taskId);
		forward("task/edit", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskName = req.getParameter("taskName");
		String finishTime = req.getParameter("finishTime");
		String taskId = req.getParameter("taskId");
		
		try {
			service.editTask(taskName, finishTime, taskId);
			AjaxResult result = AjaxResult.success();
			sendJson(result,resp);
		} catch(ServiceException e) {
			AjaxResult result = AjaxResult.error(e.getMessage());
			sendJson(result,resp);
		}
	}

}
