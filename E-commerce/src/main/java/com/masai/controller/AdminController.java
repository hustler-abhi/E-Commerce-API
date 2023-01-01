package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dao.AdminDao;
import com.masai.entity.Admin;
import com.masai.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService aservice;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> registeradmin(@RequestBody Admin admin ,@RequestParam String key){
		
		return new ResponseEntity<Admin>(aservice.registerAdmin(admin,key), HttpStatus.OK);
		
	}

}
