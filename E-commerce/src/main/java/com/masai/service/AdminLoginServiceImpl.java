package com.masai.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminDao;
import com.masai.dao.AdminSessionDao;
 
import com.masai.dto.LoginDTO;
import com.masai.entity.Admin;
import com.masai.entity.CurrentAdminSession;
 
import com.masai.exception.AdminException;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	AdminDao adao;

	@Autowired
	AdminSessionDao cdao;

	@Override
	public Admin login(LoginDTO login) throws AdminException {
		// TODO Auto-generated method stub

		Admin admin = adao.findByAdminEmail(login.getEmail());

		if (admin == null) {
			throw new AdminException("No user found with this email");
		}

		if (admin.getAdminPassword().equals(login.getPassword())){

			String key = RandomString.make();

			CurrentAdminSession curr = new CurrentAdminSession(admin.getAdminId(), key, LocalDate.now());
			cdao.save(curr);

		}else {
			
			throw new AdminException("No user found with this password");
			
		}

		return admin;
	}

	@Override
	public String logout(String key) throws AdminException {
		 
		String msg="not log out..";
	CurrentAdminSession curr=cdao.findByUuid(key);
	
	if(curr==null) {
		
		throw new AdminException("No user found");
	}else {
		msg=" log out succesfully..";
		cdao.delete(curr);
	}

		return msg;
	}

}
