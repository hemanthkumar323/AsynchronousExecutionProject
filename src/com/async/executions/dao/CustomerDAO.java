package com.async.executions.dao;

import com.async.executions.model.Customer;

public interface CustomerDAO
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
}