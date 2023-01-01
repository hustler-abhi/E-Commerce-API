package com.masai.service;

import com.masai.dto.LoginDTO;
import com.masai.entity.Admin;
import com.masai.exception.AdminException;

public interface AdminLoginService {
	
	public Admin login(LoginDTO login) throws AdminException;
	public String logout(String key) throws AdminException;

}
