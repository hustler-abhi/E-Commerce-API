package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Customer;
import com.masai.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService csao;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
		
		return new ResponseEntity<Customer>(csao.registerCustomer(customer), HttpStatus.ACCEPTED);
		
	}
	

}
