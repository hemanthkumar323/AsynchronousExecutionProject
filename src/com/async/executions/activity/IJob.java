package com.async.executions.activity;

import com.async.executions.model.Status;

public interface IJob {
	
	public void preExecute();
	
	public void execute();
	
	public void postExecute();
	
	public Status getStatus();
	

}
