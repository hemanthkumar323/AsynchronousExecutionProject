package com.mykong.activity.impl;

public class AsynchronousJob1 extends BaseJob {
	
	private static final String JOB_DESCRIPTION = "Asynchronous job 1";

	public AsynchronousJob1(String jobDescription, String createdBy) {
		super(JOB_DESCRIPTION, "system");
	}

}
