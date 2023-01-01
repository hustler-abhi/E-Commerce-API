package com.masai.service;

import com.masai.dto.LoginDTO;
import com.masai.entity.Admin;
import com.masai.entity.Customer;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;

public interface CustomerLoginService {
	
	public Customer login(LoginDTO login) throws CustomerException;
	public String logout(String key) throws CustomerException;
	

}
