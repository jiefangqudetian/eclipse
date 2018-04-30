package com.kaishengit.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.TaskDao;
import com.kaishengit.entity.Task;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;

public class TaskService {
	TaskDao taskDao = new TaskDao();
	public void saveTask(String taskName, String finishTime, int accountId) {
		// TODO Auto-generated method stub
		try {
			Task task = new Task();
			task.setTitle(taskName);
			task.setAccountId(accountId);
			task.setStatus(Config.TASK_STATUS_UNDONE);
			// 将字符串按照一定的格式转化成日期
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date finishDate = format.parse(finishTime);
			task.setFinishTime(finishDate);
			taskDao.save(task);
		} catch (ParseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Task> findTaskListByAccountId(int accountId, String show) {
		if (StringUtils.isNotEmpty(show)) {
			if (show.equals("undone")) {
				int status = Config.TASK_STATUS_UNDONE;
				return taskDao.findListByAccountIdAndStatus(accountId,status);
			}
		}
		return taskDao.findListByAccountId(accountId);
	}

	/**根据taskId修改task状态
	 * @param taskId
	 * @param status
	 */
	public void updateTaskStatusById(String taskId, String status) {
		// TODO Auto-generated method stub
		Task task = taskDao.findById(taskId);
		task.setStatus(Integer.parseInt(status));
		taskDao.update(task);
	}

	/**通过Id删除task
	 * @param taskId
	 * @param accountId
	 */
	public void delById(String taskId, int accountId) {
		// taskId如果不是数字  抛异常
		if (!StringUtils.isNumeric(taskId)) {
			throw new ServiceException("参数异常");
		}
		// 根据id找到的task为空 抛异常
		Task task = taskDao.findById(taskId);
		if (task == null) {
			throw new ServiceException("参数异常");
		}
		//相等执行删除方法，不相等抛异常
		if (task.getAccountId() == accountId) {
			taskDao.delById(Integer.parseInt(taskId));
		} else {
			throw new ForbiddenException("无权限访问");
		}
	}

	public Task findTaskById(String taskId) {
		// TODO Auto-generated method stub
		return taskDao.findTaskById(taskId);
	}

	public void editTask(String taskName, String finishTime, String taskId) {
		// TODO Auto-generated method stub
		try {
			Task task = taskDao.findById(taskId);
			task.setTitle(taskName);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date finishDate = format.parse(finishTime);
			task.setFinishTime(finishDate);
			taskDao.update(task);
		} catch (ParseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
