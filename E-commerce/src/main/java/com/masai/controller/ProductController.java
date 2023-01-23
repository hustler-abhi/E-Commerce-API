package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.ProductPageDTO;
import com.masai.entity.Product;
import com.masai.exception.CategoryException;
import com.masai.exception.ProductException;
import com.masai.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	@PostMapping("/add/{categoryId}/{uuidKey}")
	public ResponseEntity<Product> addProduct(@RequestBody Product product, @PathVariable("categoryId") int categoryId, @PathVariable("uuidKey") String key) throws ProductException, CategoryException {
		Product p=ps.addProduct(product, categoryId, key);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{productId}")
	public ResponseEntity<Product> viewProduct(@PathVariable("productId") int productId) throws ProductException {
		Product p=ps.viewProduct(productId);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewAllProduct")
	public ResponseEntity<List<Product>> allProduct() throws ProductException {
		List<Product>list=ps.allProduct();
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{productId}/{uuidKey}")
	public ResponseEntity<Product> removeProduct(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws ProductException {
		Product p=ps.removeProduct(productId, key);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{uuidKey}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("uuidKey") String key) throws ProductException {
		Product p=ps.updateProduct(product, key);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/productByName/{name}")
	public ResponseEntity<List<Product>> productByNameLike(@PathVariable("name") String name) throws ProductException {
		List<Product>list=ps.productByNameLike(name);
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/top5")
	public ResponseEntity<List<Product>> top5() throws ProductException {
		List<Product>list=ps.top5();
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/page")
	 public ResponseEntity<ProductPageDTO> allProductWithPaging(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)  int pageNumber,@RequestParam(value = "pagesize",defaultValue = "5",required = false) int pagesize) throws ProductException {
		 
		return new ResponseEntity<ProductPageDTO>(ps.allProductWithPaging(pagesize, pagesize),HttpStatus.ACCEPTED);
	}
	

}
