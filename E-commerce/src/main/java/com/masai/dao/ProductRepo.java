package com.masai.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	public List<Product> findByProductName(String name);
	public List<Product> findTop5ByOrderBySoldCountDesc();
	
	@Query("select p from Product p where p.productName like %?1% ")
	List<Product> productByNameLike(String name);

}
