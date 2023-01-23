package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminSessionDao;
import com.masai.dao.CategoryDao;
import com.masai.dao.ProductRepo;
import com.masai.dto.ProductPageDTO;
import com.masai.entity.Category;
import com.masai.entity.CurrentAdminSession;
import com.masai.entity.Product;
import com.masai.exception.CategoryException;
import com.masai.exception.ProductException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo pdao;
	
	@Autowired
	private CategoryDao cdao;
	
	@Autowired
	private AdminSessionDao adao;
	
	@Override
	public Product addProduct(Product product, int categoryId, String key) throws ProductException, CategoryException {
		// TODO Auto-generated method stub
		CurrentAdminSession curr=adao.findByUuid(key);
		
		if(curr==null) {
			throw new ProductException("please log in first....");
		}
	  Category cat=cdao.findById(categoryId).get();
	   cat.getProducts().add(product);
	    pdao.save(product);
		
		return product;
	}

	@Override
	public Product viewProduct(int productId) throws ProductException {
		// TODO Auto-generated method stub
	Product p=pdao.findById(productId).get();
	
	if(p==null) {
		throw new ProductException("no product found with this id");
	}
		return p;
	}

	@Override
	public List<Product> allProduct() throws ProductException {
		// TODO Auto-generated method stub
		
	      
	
	     List<Product>list= pdao.findAll();
	    
	   
	    
	   if(list.size()==0) {
		throw new ProductException("no product AValibale now");
	}
		return list;
	}

	@Override
	public Product removeProduct(int productId, String key) throws ProductException {
		// TODO Auto-generated method stub
		
          CurrentAdminSession curr=adao.findByUuid(key);
		
		if(curr==null) {
			throw new ProductException("please log in first....");
		}
		Product p=pdao.findById(productId).get();
		
		if(p==null) {
			throw new ProductException("no product found with this id");
		}
		pdao.delete(p);
		
		
		return p;
	}

	@Override
	public Product updateProduct(Product product, String key) throws ProductException {
       CurrentAdminSession curr=adao.findByUuid(key);
		
		if(curr==null)
			throw new ProductException("you don't have authority to update product");
		
		
		Optional<Product>p=pdao.findById(product.getProductId());
		if(p.isPresent()) {
			Product pro=p.get();
			
			if(product.getDescription()!="")
				pro.setDescription(product.getDescription());
			
			if(product.getPrice()!=null)
				pro.setPrice(product.getPrice());
			
			if(product.getProductName()!="")
				pro.setProductName(product.getProductName());
			
			if(product.getQuantity()!=null)
				pro.setQuantity(product.getQuantity());
			
			if(product.getUrl()!="")
				pro.setUrl(product.getUrl());
			
			return pdao.save(pro);
		}
		throw new ProductException("product not found with id "+product.getProductId());
	}

	@Override
	public List<Product> productByName(String name) throws ProductException {
		// TODO Auto-generated method stub
	List<Product>list=pdao.findByProductName(name);
	
	if(list.size()==0) {
		throw new ProductException("no product found with this name..");
	}
		return list;
	}

	 

	@Override
	public List<Product> top5() throws ProductException {
		// TODO Auto-generated method stub
		List<Product>list=pdao.findTop5ByOrderBySoldCountDesc();
		
		if(list.size()==0) {
			throw new ProductException("no product found with this name..");
		}
			return list;
	}

	@Override
	public ProductPageDTO allProductWithPaging(int pageNumber, int pagesize) throws ProductException {
		// TODO Auto-generated method stub
		
		org.springframework.data.domain.Pageable p= PageRequest.of(pageNumber, pagesize);
		
	Page<Product> page=pdao.findAll(p);
	
	List<Product>product=page.getContent();
	
	
	if(product.size()==0) {
		throw new ProductException("No product found");
	}
	
	ProductPageDTO pdto=new ProductPageDTO(product, page.getNumber(),page.getSize(),page.getNumberOfElements(),page.getTotalPages(),page.isLast());
		
		
		return pdto;
	}

	@Override
	public List<Product> productByNameLike(String name) throws ProductException {
		// TODO Auto-generated method stub
	List<Product> list=pdao.findByProductNameContaining(name);
	
	if(list.size()==0) {
		throw new ProductException("Product not found wiyh this search...");
	}
		return list;
	}

	 

}
