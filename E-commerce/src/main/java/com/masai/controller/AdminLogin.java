package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.LoginDTO;
import com.masai.entity.Admin;
import com.masai.exception.AdminException;
import com.masai.service.AdminLoginService;
 

@RestController
@RequestMapping("/adminlog")
public class AdminLogin {
	
	@Autowired
	AdminLoginService ls;
	
	@PostMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody LoginDTO login) {
		
		return new ResponseEntity<Admin>(ls.login(login), HttpStatus.OK);
	};
	
	@PostMapping("/logout/{k}")
	public ResponseEntity<String> logout(@PathVariable("k") String key) {
		
		return new ResponseEntity<String>(ls.logout(key), HttpStatus.OK);
		
	};
	

}
