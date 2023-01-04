package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.CartDao;
import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerSessionDao;
import com.masai.dao.ProductRepo;
import com.masai.entity.Cart;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.Customer;
import com.masai.entity.Product;
import com.masai.entity.ProductDTO;
import com.masai.exception.CartException;
import com.masai.exception.CustomerException;
import com.masai.exception.ProductException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cr;

	@Autowired
	private CustomerDao cusRepo;

	@Autowired
	private ProductRepo prepo;

	@Autowired
	private CustomerSessionDao usrRepo;

	public int quantity(List<ProductDTO> list) {

		int sum = 0;
		for (ProductDTO p : list) {
			if (p.getQuantity() != 0) {

				sum += p.getQuantity();
			}
		}
		return sum;
	}

	public int total(List<ProductDTO> list) {

		int sum = 0;
		for (ProductDTO p : list) {
			if (p.getQuantity() != 0) {

				sum += p.getQuantity() * p.getPrice();
			}
		}
		return sum;
	}

	@Override
	public Cart addCart(Cart cart, String key) throws CustomerException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = usrRepo.findByUuid(key);
		if (cs == null) {
			throw new CustomerException("please logint first..");
		}
		Customer c = cusRepo.findById(cs.getUserId()).get();
		if (c.getCart() != null) {
			throw new CustomerException("the customer has alredy a cart..");
		}
		cart.setCustomer(c);

		c.setCart(cart);

		return cr.save(cart);

	}

	@Override
	public Cart viewCart(int cartId, String key) throws CartException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = usrRepo.findByUuid(key);
		if (cs == null) {
			throw new CustomerException("please logint first..");
		}

		Cart c = cr.findById(cartId).get();

		if (c == null) {
			throw new CustomerException("cart not found");
		}

		return c;

	}

	@Override
	public Cart addItemIntoCart(int productId, String key) throws CartException, ProductException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = usrRepo.findByUuid(key);

		if (cs == null) {

			throw new CustomerException("please logint first..");
		}

		Cart c = cusRepo.findById(cs.getUserId()).get().getCart();

		Product product = prepo.findById(productId).get();
		if (product == null) {
			throw new ProductException("no product found with this id..");
		}

		ProductDTO pdto = new ProductDTO();
		pdto.setDescription(product.getDescription());
		pdto.setPrice(product.getPrice());
		pdto.setProductId(product.getProductId());
		pdto.setProductName(product.getProductName());
		pdto.setUrl(product.getUrl());
		pdto.setAvailableProduct(product.getQuantity());

		c.getProducts().add(pdto);

		boolean flag = true;

		for (ProductDTO p : c.getProducts()) {
			if (p.getProductId() == productId) {
				flag = false;
				p.setQuantity(p.getQuantity() + 1);
			}
		}
		if (flag) {
			pdto.setQuantity(1);
			c.getProducts().add(pdto);
		}

		c.setTotalPrice(quantity(c.getProducts()));
		c.setTotalItems(total(c.getProducts()));

		return cr.save(c);

	}

	@Override
	public Cart removeItemFromCart(int productId, String key) throws CartException, ProductException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = usrRepo.findByUuid(key);

		if (cs == null) {

			throw new CustomerException("please logint first..");
		}
		
        Cart cart=cusRepo.findById(cs.getUserId()).get().getCart();
		
		boolean flag=cart.getProducts().removeIf(p-> p.getProductId()==productId);
		
		if(!flag) {
			throw new ProductException("product "+productId+" is not there in cart "+cart.getCartId());
		}
		
		cart.setTotalPrice( total(cart.getProducts()));
		cart.setTotalItems( quantity(cart.getProducts()));
		
		return cr.save(cart);
		 
	}

	@Override
	public Cart increaseQuantity(int productId, String key) throws CartException, ProductException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = usrRepo.findByUuid(key);

		if (cs == null) {

			throw new CustomerException("please logint first..");
		}
		
        Cart cart=cusRepo.findById(cs.getUserId()).get().getCart();
        Optional<Product>pro=prepo.findById(productId);
		if(pro.isEmpty()) {
			throw new ProductException("product mot foumd with id "+productId);
		}
		
	 
		
		if(cart.getCustomer().getCustomerId()!=cs.getUserId())
			throw new CartException("user mismatch please try again");

		
		cart.getProducts().forEach(p->{
			if(p.getProductId()==productId) {
				p.setQuantity(p.getQuantity()+1);
			}
		});
		cart.setTotalPrice(total(cart.getProducts()));
		cart.setTotalItems(quantity(cart.getProducts()));
		return cr.save(cart);
		
		 
	}

	@Override
	public Cart decreaseQuantity(int productId, String key) throws CartException, ProductException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = usrRepo.findByUuid(key);

		if (cs == null) {

			throw new CustomerException("please logint first..");
		}
		
        Cart cart=cusRepo.findById(cs.getUserId()).get().getCart();
        Optional<Product>pro=prepo.findById(productId);
		if(pro.isEmpty()) {
			throw new ProductException("product mot foumd with id "+productId);
		}
		
	 
		
		if(cart.getCustomer().getCustomerId()!=cs.getUserId())
			throw new CartException("user mismatch please try again");

		
		cart.getProducts().forEach(p->{
			if(p.getProductId()==productId) {
				p.setQuantity(p.getQuantity()-1);
				if(p.getQuantity()<1) {
					p.setQuantity(1);
				}
			}
		});
		cart.setTotalPrice(total(cart.getProducts()));
		cart.setTotalItems(quantity(cart.getProducts()));
		return cr.save(cart);
		
	}

	@Override
	public Cart clearCart(String key) throws CartException {
		// TODO Auto-generated method stub
		CurrentUserSession cs = usrRepo.findByUuid(key);
		if (cs == null) {
			throw new CustomerException("please logint first..");
		}

		Cart c = cusRepo.findById(cs.getUserId()).get().getCart();

		c.getProducts().clear();
		c.setTotalItems(0);
		c.setTotalPrice(0);

		return cr.save(c);
	}
 

}
