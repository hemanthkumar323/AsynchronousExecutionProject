package com.mykong.activity;

import com.mkyong.customer.model.Status;

public interface ITask {

	public void process();

	public Status getStatus();
}
