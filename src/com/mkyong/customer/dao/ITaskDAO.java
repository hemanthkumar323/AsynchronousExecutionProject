package com.mkyong.customer.dao;

import com.mkyong.customer.model.Status;
import com.mkyong.customer.model.Task;

public interface ITaskDAO {
	public void createTask(Task task);

	public Task getTask(String taskId);

	public void updateStatus(String taskId, Status status);
	
	public Status getStatus(String taskId);

}
