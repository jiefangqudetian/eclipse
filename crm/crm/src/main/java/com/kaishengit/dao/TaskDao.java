package com.kaishengit.dao;


import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.Task;
import com.kaishengit.util.DbHelp;

public class TaskDao {

	/**保存待办事项
	 * @param task
	 */
	public void save(Task task) {
		String sql = "insert into t_task (title, finish_time, status, account_id) values (?,?,?,?)";
		//String sql = "insert into t_task (title,finish_time,status,account_id) values (?,?,?,?)";
		DbHelp.executeUpdate(sql, task.getTitle(),task.getFinishTime(),task.getStatus(),task.getAccountId());
	}
	

	public Task findById(String taskId) {
		String sql = "select * from t_task where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Task.class,new BasicRowProcessor(new GenerousBeanProcessor())), taskId);
	}

	public void update(Task task) {
		String sql = "update t_task set title = ?,finish_time = ?,status = ? where id = ?";
		DbHelp.executeUpdate(sql, task.getTitle(),task.getFinishTime(),task.getStatus(),task.getId());
		
	}

	public void delById(int taskId) {
		// TODO Auto-generated method stub
		String sql = "delete from t_task where id = ?";
		DbHelp.executeUpdate(sql, taskId);
		
	}
	public List<Task> findListByAccountId(int accountId) {
		String sql = "select * from t_task where account_id = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Task.class,new BasicRowProcessor(new GenerousBeanProcessor())), accountId);
		
	}

	public List<Task> findListByAccountIdAndStatus(int accountId, int status) {
		String sql = "select * from t_task where account_id = ? and status = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Task.class,new BasicRowProcessor(new GenerousBeanProcessor())), accountId,status);
	}


	public Task findTaskById(String id) {
		String sql = "select * from t_task where id = ?";
		return DbHelp.query(sql,new BeanHandler<>(Task.class,new BasicRowProcessor(new GenerousBeanProcessor())) , Integer.parseInt(id));
	}

}
