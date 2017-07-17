package com.mkyong.customer.dao;

import java.util.List;

import com.mkyong.customer.model.Job;
import com.mkyong.customer.model.Status;
import com.mkyong.customer.model.Task;

public interface IJobDAO {
	public void createJob(Job job);

	public Job getJob(String jobId);
	
	public List<Task> getChildTasks(String jobId);

	public void updateStatus(String jobId, Status status);
	
	public Status getStatus(String jobId);
}
