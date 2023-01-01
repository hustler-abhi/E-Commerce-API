package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Customer;

public interface CustomerDao  extends JpaRepository<Customer, Integer>{
	
	public Customer findByEmail(String email);

}
