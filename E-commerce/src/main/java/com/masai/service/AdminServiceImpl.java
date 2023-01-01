package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminDao;
import com.masai.entity.Admin;
import com.masai.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDao adao;

	@Override
	public Admin registerAdmin(Admin admin,String valid) throws AdminException {
		// TODO Auto-generated method stub
		if(admin==null) {
			throw new AdminException("admin not registered");
		}
		if(valid.equals("admin")) {
			return adao.save(admin);
			
		}else {
			throw new AdminException("u have not authority to register as admin");
		}
		
		 
	}
	

}
