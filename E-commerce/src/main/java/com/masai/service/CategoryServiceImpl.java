package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminSessionDao;
import com.masai.dao.CategoryDao;
import com.masai.entity.Admin;
import com.masai.entity.Category;
import com.masai.entity.CurrentAdminSession;
import com.masai.entity.Product;
import com.masai.exception.CategoryException;

@Service
public class CategoryServiceImpl  implements CategoryService{
	
	@Autowired
	private CategoryDao cdao;
	
	@Autowired
	private AdminSessionDao adao;

	@Override
	public Category addCategory(Category category, String key) throws CategoryException {
		// TODO Auto-generated method stub
	   CurrentAdminSession admin=adao.findByUuid(key);
		if(admin==null) {
			throw new CategoryException("please login first");
		}
	Category cat=cdao.findByName(key);
	if(cat.getName()==category.getName()) {
		throw new CategoryException("this category is already registered..");
	}
		
		return cdao.save(category);
		 
	}

	@Override
	public Category viewCategory(int categoryId) throws CategoryException {
		// TODO Auto-generated method stub
		
	Optional<Category>opt=cdao.findById(categoryId);
	
	if(opt.isEmpty()) {
		throw new CategoryException("no category found with this id");
	}
		return opt.get();
	}

	@Override
	public Category deleteCategory(int categoryId, String key) throws CategoryException {
		// TODO Auto-generated method stub
		CurrentAdminSession admin=adao.findByUuid(key);
		if(admin==null) {
			throw new CategoryException("please login first");
		}
		Optional<Category>opt=cdao.findById(categoryId);
		
		if(opt.isEmpty()) {
			throw new CategoryException("no category found with this id");
		}
			 cdao.delete(opt.get());
		
		return opt.get();
	}

	@Override
	public List<Category> allCategory() throws CategoryException {
		// TODO Auto-generated method stub
		
		List<Category>list =cdao.findAll();
		
		if(list.size()==0) {
			throw new CategoryException("no category avalaible now..");
		}
		
		return list;
	}

	@Override
	public List<Product> productByCategory(int categoryId) throws CategoryException {
		// TODO Auto-generated method stub
      Optional<Category>opt=cdao.findById(categoryId);
		
		if(opt.isEmpty()) {
			throw new CategoryException("no category found with this id");
		}
			if(opt.get().getProducts().isEmpty()) {
				throw new CategoryException("no category found with this id");
			}
		
		return opt.get().getProducts();
	}

}
