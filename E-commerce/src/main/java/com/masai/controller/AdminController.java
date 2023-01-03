package com.masai.controller;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Admin;
import com.masai.exception.AdminException;
import com.masai.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService as;
	
	@PostMapping("/signup")
	public ResponseEntity<Admin> registeradmin(@RequestBody Admin admin ,@RequestParam String key){
		
		return new ResponseEntity<Admin>(as.registerAdmin(admin,key), HttpStatus.OK);
		
	}
	
	@GetMapping("/viewAllAdmin")
	public ResponseEntity<List<Admin>> viewAllAdmin(String key)throws AdminException{
		List<Admin>list=as.viewAllAdmin(key);
		return new ResponseEntity<List<Admin>>(list,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAdmin")
	public ResponseEntity<Admin> deleteAdmin(Admin admin, String key)throws AdminException{
		Admin ad=as.deleteAdmin(admin, key);
		return new ResponseEntity<Admin>(ad,HttpStatus.ACCEPTED);
	}

}
