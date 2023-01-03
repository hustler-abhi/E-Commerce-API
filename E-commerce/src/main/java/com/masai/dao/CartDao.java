package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {

}
