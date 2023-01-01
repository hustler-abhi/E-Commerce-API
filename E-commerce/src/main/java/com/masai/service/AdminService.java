package com.masai.service;

import com.masai.entity.Admin;
import com.masai.exception.AdminException;

public interface AdminService {
	
	public Admin registerAdmin(Admin admin,String valid) throws AdminException;
	

}
