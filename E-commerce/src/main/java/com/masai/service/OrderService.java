package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.entity.Order;
import com.masai.exception.AdminException;
import com.masai.exception.CartException;
import com.masai.exception.OrderException;

public interface OrderService {
	
  public Order addOrder(String uuid)throws OrderException,CartException;
	
	public Order viewOrder(int orderId)throws OrderException;
	
	public List<Order> viewOrdersByDate(LocalDate startDate, LocalDate endDate)throws OrderException;
	
	public Order updateOrderStatus(int orderId, String status)throws OrderException;
	
	public Order deleteOrder(int orderId)throws OrderException;
	
	public List<Order> viewAllOrder()throws OrderException;

}
