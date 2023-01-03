package com.masai.service;

import java.util.List;

import com.masai.entity.Category;
import com.masai.entity.Product;
import com.masai.exception.CategoryException;

public interface CategoryService {
	

	public Category addCategory(Category category, String key)throws CategoryException;
	
	public Category viewCategory(int categoryId)throws CategoryException;
	
	public Category deleteCategory(int categoryId, String key)throws CategoryException;
	
	public List<Category> allCategory()throws CategoryException;
	
	public List<Product> productByCategory(int categoryId)throws CategoryException;

}
