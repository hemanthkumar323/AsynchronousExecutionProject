package com.mykong.activity;

import com.mkyong.customer.model.Status;

public interface IJob {
	
	public void preExecute();
	
	public void execute();
	
	public void postExecute();
	
	public Status getStatus();
	

}
