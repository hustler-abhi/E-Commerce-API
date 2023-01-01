package com.masai.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerSessionDao;
import com.masai.dto.LoginDTO;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.Customer;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private CustomerSessionDao csdao;

	@Override
	public Customer login(LoginDTO login) throws CustomerException {
		// TODO Auto-generated method stub

		Customer customer = cdao.findByEmail(login.getEmail());

		if (customer == null) {

			throw new CustomerException("please signup first...");

		}
		if (customer.getPassword().equals(login.getPassword())) {

			CurrentUserSession curr = new CurrentUserSession();
			curr.setUserId(customer.getCustomerId());
			curr.setUuid(RandomString.make());
			curr.setLocalDateTime(LocalDateTime.now());

			csdao.save(curr);

			return customer;

		} else {
			throw new CustomerException("wrong password....");
		}

	}

	@Override
	public String logout(String key) throws CustomerException {
		// TODO Auto-generated method stub
		
	CurrentUserSession curr=csdao.findByUuid(key);
	
	String msg="not logged out";
	
	if(curr==null) {
		throw new CustomerException("user not logged..");
	}
	else {
		
		csdao.delete(curr);
		msg="log out succesfully..";
	}
		return msg;
	}

}
