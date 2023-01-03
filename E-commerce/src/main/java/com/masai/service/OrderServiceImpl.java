package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.OrderDao;
import com.masai.entity.Order;
import com.masai.exception.AdminException;
import com.masai.exception.CartException;
import com.masai.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao odao;

	@Override
	public Order addOrder(String uuid) throws OrderException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order viewOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		Optional<Order> opt = odao.findById(orderId);

		if (opt.isEmpty()) {
			throw new OrderException("no order found with this id..");
		}
		return opt.get();
	}

	@Override
	public List<Order> viewOrdersByDate(LocalDate startDate, LocalDate endDate) throws OrderException {
		// TODO Auto-generated method stub
		List<Order> list = odao.getAllorders(startDate, endDate);
		if (list.size() == 0) {
			throw new OrderException("no order found between this date..");
		}
		return list;
	}

	@Override
	public Order updateOrderStatus(int orderId, String status) throws OrderException {
		// TODO Auto-generated method stub
		Optional<Order> opt = odao.findById(orderId);

		if (opt.isEmpty()) {
			throw new OrderException("no order found with this id..");
		}
		opt.get().setStatus(status);
		return opt.get();

	}

	@Override
	public Order deleteOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		Optional<Order> opt = odao.findById(orderId);

		if (opt.isEmpty()) {
			throw new OrderException("no order found with this id..");
		}
		  odao.delete(opt.get());
		return opt.get();
	}

	@Override
	public List<Order> viewAllOrder() throws OrderException {
		// TODO Auto-generated method stub
		List<Order> list=odao.findAll();
		if(list.size()==0) {
			throw new OrderException("zero order till now ..");
		}
		return list;
	}

}
