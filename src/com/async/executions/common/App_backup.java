package com.async.executions.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.async.executions.config.EnvConfig;
import com.async.executions.dao.CustomerDAO;
import com.async.executions.model.Customer;

public class App_backup
{
    public static void main( String[] args )
    {
    	ApplicationContext context =
    		new AnnotationConfigApplicationContext(EnvConfig.class);

        CustomerDAO customerDAO = context.getBean(CustomerDAO.class);
        Customer customer = new Customer(1, "mkyong",28);
        customerDAO.insert(customer);

        Customer customer1 = customerDAO.findByCustomerId(1);
        System.out.println(customer1);

    }
}