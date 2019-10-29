package com.ace.jdbcDemo.repository;

public class CustomerConstants {

	static String INSERT_CUSTOMER="insert into customer(cust_id,cust_name,email,mobile,create_date) values(?,?,?,?,?)";
	static String FIND_BY_CUSTID="select * from customer where cust_id= ?";
}
