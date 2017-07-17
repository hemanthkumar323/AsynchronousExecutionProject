package com.mykong.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.dao.IJobDAO;
import com.mkyong.customer.dao.ITaskDAO;
import com.mkyong.customer.dao.impl.JdbcCustomerDAO;
import com.mkyong.customer.dao.impl.JobDAOImpl;
import com.mkyong.customer.dao.impl.TaskDAOImpl;

@Configuration
public class EnvConfig {

	@Bean
	public DriverManagerDataSource getDataSource() {
		return new DriverManagerDataSource("jdbc:mysql://localhost:3306/schema_dev", "root", "manager");
	}
	
	@Bean
	public CustomerDAO getCustomerDAO() {
		return new JdbcCustomerDAO(getDataSource());
	}
	
	@Bean
	@Qualifier("jobDAOImpl")
	IJobDAO getJobDAO() {
		return new JobDAOImpl(getDataSource());
	}
	
	@Bean
	@Qualifier("taskDAOImpl")
	ITaskDAO getTaskDAO() {
		return new TaskDAOImpl(getDataSource());
	}
	
	@Bean
	ExecutorService executorService() {
		return Executors.newFixedThreadPool(10);
	}

}
