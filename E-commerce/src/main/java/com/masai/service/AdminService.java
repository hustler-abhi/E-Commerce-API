package com.masai.service;

import java.util.List;

import com.masai.entity.Admin;
import com.masai.exception.AdminException;

public interface AdminService {
	
	public Admin registerAdmin(Admin admin,String valid) throws AdminException;
	
     public List<Admin> viewAllAdmin(String key)throws AdminException;
	public Admin deleteAdmin(Admin admin, String key)throws AdminException;

}
