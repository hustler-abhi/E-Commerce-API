package com.masai.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Order;
import com.masai.exception.AdminException;
import com.masai.exception.CartException;
import com.masai.exception.OrderException;
import com.masai.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService os;
	
	@PostMapping("/addOrder/{uuid}")
	public ResponseEntity<Order> addOrder(@PathVariable("uuid") String uuid) throws OrderException, CartException {
		 
		return new ResponseEntity<Order>(os.addOrder(uuid),HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewOrder/{orderId}")
	public ResponseEntity<Order> viewOrder(@PathVariable("orderId") int orderId) throws OrderException {
		 
		return new ResponseEntity<Order>(os.viewOrder(orderId),HttpStatus.ACCEPTED);
	}

	@GetMapping("/viewOrdersByDate/{startDate}/{endDate}")
	public ResponseEntity<List<Order>> viewOrdersByDate(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws OrderException {
		 
		return new ResponseEntity<List<Order>>(os.viewOrdersByDate(startDate, endDate),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewAllOrder")
	public ResponseEntity<List<Order>> viewAllOrder() throws OrderException, AdminException {
		List<Order> list=os.viewAllOrder();
		return new ResponseEntity<List<Order>>(list,HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateOrderStatus/{orderId}/{status}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable("orderId") int orderId, @PathVariable("status") String status) throws OrderException {
		Order o=os.updateOrderStatus(orderId, status);
		return new ResponseEntity<Order>(o,HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") int orderId) throws OrderException {
		Order o=os.deleteOrder(orderId);
		return new ResponseEntity<Order>(o,HttpStatus.ACCEPTED);
	}

}
