package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.CartDao;
import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerSessionDao;
import com.masai.entity.Address;
import com.masai.entity.Cart;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.Customer;
import com.masai.entity.Order;
import com.masai.exception.CustomerException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private CustomerSessionDao csd;
	
	@Autowired
	private CartDao cart;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		if (customer == null) {
			throw new CustomerException("customer not found");
		}
		Cart c=new Cart();
		customer.setCart(c);
		c.setCustomer(customer);
		cart.save(c);
		cdao.save(customer);
		return customer;
	}

	@Override
	public Customer viewCustomer(String key) throws CustomerException {
		// TODO Auto-generated method stub
		CurrentUserSession customer = csd.findByUuid(key);

		if (customer == null) {
			throw new CustomerException("please login first..");
		}

		Optional<Customer> opt = cdao.findById(customer.getUserId());

		return opt.get();
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		// TODO Auto-generated method stub

		CurrentUserSession cs = csd.findByUuid(key);

		if (customer == null) {
			throw new CustomerException("please login first..");
		}
     Optional<Customer> c=cdao.findById(cs.getUserId());
		
		if(!c.isPresent()) {
			throw new CustomerException("user not found");
		}
		
		Customer pre=c.get();
		Address preA=pre.getAddress();
		
		Address newA=customer.getAddress();
		if(newA.getCity()!=null) {
			preA.setCity(newA.getCity());
		}
		if(newA.getCountry()!=null) {
			preA.setCountry(newA.getCountry());		
		}
		if(newA.getPincode()!=null) {
			preA.setPincode(newA.getPincode());
		}
		if(newA.getState()!=null) {
			preA.setState(newA.getState());
		}
		
		if(customer.getCustomerName()!=null){
			pre.setCustomerName(customer.getCustomerName());
		}
		if(customer.getEmail()!=null){
			pre.setEmail(customer.getEmail());
		}
		if(customer.getMobile()!=null){
			pre.setMobile(customer.getMobile());
		}
		if(customer.getPassword()!=null){
			pre.setPassword(customer.getPassword());
		}
		
		pre.setAddress(preA);
		return cdao.save(pre);

		 
	}

	@Override
	public Customer deleteCustomer(int customerId, String key) throws CustomerException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = csd.findByUuid(key);

		if (cs == null) {
			throw new CustomerException("please login first..");
		}

		Optional<Customer> opt = cdao.findById(customerId);
		if (opt.isEmpty()) {

			throw new CustomerException("no customerfound to delete");
		}

		cdao.delete(opt.get());
		return opt.get();
	}

	@Override
	public List<Order> viewOrders(String key) throws CustomerException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = csd.findByUuid(key);

		if (cs == null) {
			throw new CustomerException("please login first..");
		}

		Optional<Customer> opt = cdao.findById(cs.getUserId());
		if (opt.isEmpty()) {

			throw new CustomerException("no customerfound to delete");
		}
		if(opt.get().getOrders().isEmpty()) {
			throw new CustomerException("no order till now..");
		}

		return opt.get().getOrders();
	}

}
