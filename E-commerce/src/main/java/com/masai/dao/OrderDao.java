package com.masai.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

	@Query("from Order where orderDate>=?1 and orderDate<=?2")
	public List<Order>getAllorders(LocalDate l1,LocalDate l2);
}
