package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Cart;
import com.masai.exception.CartException;
import com.masai.exception.ProductException;
import com.masai.service.CartService;
@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cs;
	
	@GetMapping("/view/{uuidKey}")
	public ResponseEntity<Cart> viewCart(  @RequestParam("id") Integer id,@PathVariable("uuidKey") String key) throws CartException {
		 
		return new ResponseEntity<Cart>(cs.viewCart(id, key),HttpStatus.ACCEPTED);
	}

 
	@PutMapping("/addItemIntoCart/{productId}/{uuidKey}")
	public ResponseEntity<Cart> addItemIntoCart(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.addItemIntoCart(productId,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/removeItemFromCart/{productId}/{uuidKey}")
	public ResponseEntity<Cart> removeItemFromCart(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.removeItemFromCart(productId,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/increaseQuantity/{productId}/{uuidKey}")
	public ResponseEntity<Cart> increaseQuantity(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.increaseQuantity(productId, key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/decreaseQuantity/{productId}/{uuidKey}")
	public ResponseEntity<Cart> decreaseQuantity(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.decreaseQuantity(productId, key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/clearCart/{uuidKey}")
	public ResponseEntity<Cart> clearCart(@PathVariable("uuidKey") String key) throws CartException {
		Cart c=cs.clearCart(key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

}
