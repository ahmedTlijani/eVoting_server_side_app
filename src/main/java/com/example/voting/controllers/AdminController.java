package com.example.voting.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.voting.entities.Admin;
import com.example.voting.services.AdminService;
 

@RestController
@RequestMapping("api/admin/")
@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
public class AdminController {
		
		@Autowired
		AdminService adminService;
	
		// get all People
		@RequestMapping("/getAll")
		public List<Admin> getAllMembers()
		{
			return this.adminService.getAdmins();
		}
		
		
		// get single admin 
		
		@GetMapping("/get/{id}") 
		 Optional<Admin> getOnePeople(@PathVariable int id) {
			return this.adminService.getOneAdminById(id);
		}
	
		
		// get element by username and password 
		
		@PostMapping("/get-by-username") 
		public Admin getElementByUsernameAndPassowrd(@RequestBody Admin admin) {
			System.out.println("----------------------------------------------");
			System.out.println(admin);
			return this.adminService.getElementByUsernameAndPassowrd(admin.getUsername(), admin.getPassword());
		}
		
		
}






















