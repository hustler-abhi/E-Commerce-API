package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

	public Category findByName(String name);
}
