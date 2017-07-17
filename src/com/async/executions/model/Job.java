package com.async.executions.model;

public class Job {

	String jobId;
	String description;
	Status status;
	String createdBy;
	String dateCreated;
	
	public Job(String jobId, String description, String status, String createdBy, String dateCreated) {
		this.jobId = jobId;
		this.description = description;
		this.status = Status.valueOf(status);
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
	}

	public Job(String jobId, String description, Status status, String createdBy, String dateCreated) {
		this.jobId = jobId;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

}
