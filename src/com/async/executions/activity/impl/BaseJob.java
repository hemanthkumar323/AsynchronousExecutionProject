package com.async.executions.activity.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.async.executions.activity.IJob;
import com.async.executions.dao.impl.JobDAOImpl;
import com.async.executions.model.Job;
import com.async.executions.model.Status;

public class BaseJob implements IJob {

	@Autowired
	@Qualifier("jobDAOImpl")
	private JobDAOImpl jobDAOImpl;
	private final Job jobInfo;

	public BaseJob(String jobDescription, String createdBy) {
		UUID random = UUID.randomUUID();

		Job job = new Job(random.toString(), jobDescription, Status.RUNNING, createdBy, String.valueOf(Instant.now()));
		this.jobInfo = job;
	}

	@Override
	public void preExecute() {
		// Create a record in job table
		jobDAOImpl.createJob(jobInfo);

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postExecute() {
		jobDAOImpl.updateStatus(jobInfo.getJobId(), Status.COMPLETED);

	}

	@Override
	public Status getStatus() {
		return jobDAOImpl.getStatus(jobInfo.getJobId());
	}

}
