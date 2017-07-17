package com.mykong.activity.impl;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mkyong.customer.dao.impl.TaskDAOImpl;
import com.mkyong.customer.model.Status;
import com.mkyong.customer.model.Task;
import com.mykong.activity.ITask;

public class BaseTask implements ITask {
	
	@Autowired @Qualifier("taskDAOImpl") 
	private TaskDAOImpl taskDAOImpl;
	
	@Autowired Executor executor;
	private final Task taskInfo;
	
	public BaseTask(String parentJobId, String description) {
		UUID random = UUID.randomUUID();
		Task task = new Task(random.toString(), parentJobId, description, Status.RUNNING);
		this.taskInfo = task;
	}
	
	public void preExecute() {
		System.out.println(taskDAOImpl == null);
		System.out.println(executor == null);
		taskDAOImpl.createTask(taskInfo);
		
	}

	public void execute() {
		// TODO Auto-generated method stub
		
	}

	public void postExecute() {
		taskDAOImpl.updateStatus(taskInfo.getTaskId(), Status.COMPLETED);
		
	}

	@Override
	public Status getStatus() {
		return taskDAOImpl.getStatus(taskInfo.getTaskId());
	}

	@Override
	public void process() {
		preExecute();
		Supplier<String> supplier = () -> { return "123"; };
		CompletableFuture.supplyAsync(supplier, executor)
			.thenAcceptAsync((x) -> System.out.println(x), executor);
		
		
	}

}
