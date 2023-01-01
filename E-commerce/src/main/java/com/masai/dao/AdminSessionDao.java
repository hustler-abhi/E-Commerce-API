package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Admin;
import com.masai.entity.CurrentAdminSession;
 
import com.masai.exception.AdminException;

public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer>{
	
	public CurrentAdminSession  findByUuid(String uuid)throws AdminException;

	

}
