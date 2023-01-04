package com.masai.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.CartDao;
import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerSessionDao;
import com.masai.dao.OrderDao;
import com.masai.dao.ProductRepo;
import com.masai.entity.Cart;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.Customer;
import com.masai.entity.Order;
import com.masai.entity.Product;
import com.masai.entity.ProductDTO;
import com.masai.exception.CartException;
import com.masai.exception.OrderException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao odao;
	@Autowired
	private CartDao cartRepo;
	
	@Autowired
	private CustomerSessionDao usRepo;
	
	@Autowired
	private CustomerDao cr;
	
	@Autowired
	private ProductRepo prepo;

	@Override
	public Order addOrder(String uuid) throws OrderException, CartException {
		// TODO Auto-generated method stub
		CurrentUserSession extCu=usRepo.findByUuid(uuid);
		if(extCu==null)
			throw new OrderException("key is not valid");
		
		Customer c=cr.findById(extCu.getUserId()).get();
		
		if(c==null) {
			throw new OrderException("user not found");
		}
		
		 
		
		Cart car=cartRepo.findById(c.getCart().getCartId()).get();
		if(car==null) {
			throw new CartException("cart not found");
		}
		
		 
		
		if(car.getProducts().size()==0) {
			throw new OrderException("please add product in cart");
		}
		
		Order order=new Order();
		
		order.setCustomer(c);
		order.setOrderDate(LocalDate.now());
		order.setOrderTime(LocalTime.now());
		order.setStatus("pending");
		
		List<ProductDTO>temp=new ArrayList<>();
		
		for(ProductDTO p:car.getProducts()) {
			if(p.getAvailableProduct()<p.getQuantity()) {
				throw new OrderException(p.getProductName()+" out of stock");
			}
			temp.add(p);
		}
		
		order.setProducts(temp);
		
		List<ProductDTO>list=car.getProducts();
		
		list.forEach(p->{
			Product pro=prepo.findById(p.getProductId()).get();
			pro.setSoldCount(pro.getSoldCount()+p.getQuantity());
			pro.setQuantity(pro.getQuantity()-p.getQuantity());
			prepo.save(pro);
		});
		
		Order o=odao.save(order);
		
		car.getProducts().clear();
		car.setTotalItems(0);
		car.setTotalPrice(0);
		cartRepo.save(car);
		
		return o;
		
		 
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
