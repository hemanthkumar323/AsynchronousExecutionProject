package com.mkyong.customer.model;

public class Task {
	
	String taskId;
	String parentJobId;
	String description;
	Status status;
	
	public Task(String taskId, String parentJobId, String description, String status) {
		this.taskId = taskId;
		this.parentJobId = parentJobId;
		this.description = description;
		this.status = Status.valueOf(status);
	}
	
	public Task(String taskId, String parentJobId, String description, Status status) {
		this.taskId = taskId;
		this.parentJobId = parentJobId;
		this.description = description;
		this.status = status;
	}
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getParentJobId() {
		return parentJobId;
	}
	public void setParentJobId(String parentJobId) {
		this.parentJobId = parentJobId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
