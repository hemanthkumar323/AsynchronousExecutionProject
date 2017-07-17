package com.async.executions.activity;

import com.async.executions.model.Status;

public interface ITask {

	public void process();

	public Status getStatus();
}
