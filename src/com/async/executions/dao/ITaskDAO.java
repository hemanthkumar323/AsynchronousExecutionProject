package com.async.executions.dao;

import com.async.executions.model.Status;
import com.async.executions.model.Task;

public interface ITaskDAO {
	public void createTask(Task task);

	public Task getTask(String taskId);

	public void updateStatus(String taskId, Status status);
	
	public Status getStatus(String taskId);

}
