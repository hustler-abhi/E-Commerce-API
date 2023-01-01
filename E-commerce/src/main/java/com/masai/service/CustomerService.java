package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.entity.Order;
import com.masai.exception.CustomerException;

public interface CustomerService {
	

	public Customer registerCustomer(Customer customer)throws CustomerException;
	
	public Customer viewCustomer(String key)throws CustomerException;
	
	public Customer updateCustomer(Customer customer, String key)throws CustomerException;
	
	public Customer deleteCustomer(int customerId, String key)throws CustomerException;
	
	public List<Order> viewOrders(String key)throws CustomerException;

}
