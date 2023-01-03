package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.LoginDTO;
import com.masai.entity.Admin;
import com.masai.entity.Customer;
import com.masai.service.CustomerLoginService;

@RestController
@RequestMapping("/userlog")
public class CustomerLogin {
	
	@Autowired
	private CustomerLoginService cservice;
	
	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody LoginDTO login) {
		
		return new ResponseEntity< Customer>(cservice.login(login), HttpStatus.OK);
	};
	
	@PostMapping("/logout/{k}")
	public ResponseEntity<String> logout(@PathVariable("k") String key) {
		
		return new ResponseEntity<String>(cservice.logout(key), HttpStatus.OK);
		
	};
	

}
