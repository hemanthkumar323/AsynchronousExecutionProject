package com.mkyong.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mkyong.customer.dao.IJobDAO;
import com.mkyong.customer.model.Job;
import com.mkyong.customer.model.Status;
import com.mykong.activity.impl.BaseTask;
import com.mykong.config.EnvConfig;

public class App {
	
	public static void main( String[] args )
    {
    	ApplicationContext context =
    		new AnnotationConfigApplicationContext(EnvConfig.class);

    	/**
        IJobDAO jobDAO = context.getBean(IJobDAO.class);
        Job job = new Job("3", "test desc", Status.RUNNING.name(), null, null);
        jobDAO.createJob(job);

        Job job1 = jobDAO.getJob("3");
        System.out.println(job1.getStatus());
        
        jobDAO.updateStatus("3", Status.COMPLETED);
        Job job2 = jobDAO.getJob("3");
        System.out.println(job2.getStatus());
        */
    	
    	BaseTask baseTask = new BaseTask("123", "ahakh");
    	baseTask.process();

    }

}
