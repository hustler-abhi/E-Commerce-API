package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Customer;
import com.masai.entity.Order;
import com.masai.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService csao;
	
	@PostMapping("/signup")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
		
		return new ResponseEntity<Customer>(csao.registerCustomer(customer), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/viewprofile/{uid}")
	public ResponseEntity<Customer> viewCustomer( @PathVariable("uid") String key){
		
		return new ResponseEntity<Customer>(csao.viewCustomer(key), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{uid}")
	public  ResponseEntity<Customer> deleteCustomer(@RequestParam  int customerId,@PathVariable("uid") String key) {
		
		return new ResponseEntity<Customer>(csao.deleteCustomer(customerId, key), HttpStatus.OK);
	}
	
	@GetMapping("/vieworder/{uid}")
	public  ResponseEntity< List<Order>> viewOrders(String key){
		
		return new ResponseEntity<List<Order>>(csao.viewOrders(key), HttpStatus.OK);
	}
	
	

}
