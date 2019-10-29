package com.ace.jdbcDemo.repository;

import com.ace.jdbcDemo.entity.Customer;

public interface CustomerRepository {
	
	public boolean saveCustomer(Customer customer);
	
	public Customer getByCustId(String CustId);
	
	
	
	

}
