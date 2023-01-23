package com.masai.dto;

import java.util.List;

import com.masai.entity.Product;
import com.masai.entity.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageDTO {
	
	private List<Product> product;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean lastpage;

}
