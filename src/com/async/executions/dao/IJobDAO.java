package com.async.executions.dao;

import java.util.List;

import com.async.executions.model.Job;
import com.async.executions.model.Status;
import com.async.executions.model.Task;

public interface IJobDAO {
	public void createJob(Job job);

	public Job getJob(String jobId);
	
	public List<Task> getChildTasks(String jobId);

	public void updateStatus(String jobId, Status status);
	
	public Status getStatus(String jobId);
}
