package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Admin;
import com.masai.exception.AdminException;

public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	public Admin findByAdminEmail(String email)throws AdminException;
	
	

}
