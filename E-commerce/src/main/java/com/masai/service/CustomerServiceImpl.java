package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.CustomerDao;
import com.masai.entity.Customer;
import com.masai.entity.Order;
import com.masai.exception.CustomerException;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao cdao;
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		if(customer==null) {
			throw new CustomerException("customer not found");
		}
		cdao.save(customer);
		return customer;
	}

	@Override
	public Customer viewCustomer(String key) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(int customerId, String key) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> viewOrders(String key) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
