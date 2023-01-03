package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminSessionDao;
import com.masai.entity.Admin;
import com.masai.entity.CurrentAdminSession;
import com.masai.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adao;
	
	@Autowired
	private AdminSessionDao asdao;
	
	

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

	@Override
	public List<Admin> viewAllAdmin(String key) throws AdminException {
		// TODO Auto-generated method stub
		CurrentAdminSession curr= asdao.findByUuid(key);
		if(curr==null) {
			throw new AdminException("please login first");
		}
		if(adao.findAll().isEmpty()) {
			throw new AdminException("no admin found");
		};
		
		return adao.findAll();
	}

	@Override
	public Admin deleteAdmin(Admin admin, String key) throws AdminException {
		// TODO Auto-generated method stub
		CurrentAdminSession curr= asdao.findByUuid(key);
		if(curr==null) {
			throw new AdminException("please login first");
		}
		
      Optional<Admin> ad=adao.findById(admin.getAdminId());
      
      if(ad.isPresent()) {
    	  adao.delete(admin);
      }
		return admin;
	}
	

}
