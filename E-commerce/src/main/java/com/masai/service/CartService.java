package com.masai.service;

import com.masai.entity.Cart;
import com.masai.exception.CartException;
import com.masai.exception.CustomerException;
import com.masai.exception.ProductException;

public interface CartService {
	
public Cart addCart(Cart cart,String key)throws CustomerException;
	
	public Cart viewCart(int cartId,String key)throws CartException;
	
	public Cart addItemIntoCart(int productId,String key)throws CartException,ProductException;
	
	public Cart removeItemFromCart(int productId,String key)throws CartException,ProductException;
	
	public Cart increaseQuantity(int productId, String key)throws CartException,ProductException;
	
	public Cart decreaseQuantity(int productId, String key)throws CartException,ProductException;
	
	public Cart clearCart(String key)throws CartException;
	
	 

}
