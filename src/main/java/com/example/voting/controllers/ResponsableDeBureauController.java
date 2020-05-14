package com.example.voting.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.voting.entities.ResponsableDeBureau;
import com.example.voting.repository.ResponsableDeBureauRepository;
import com.example.voting.services.ResponsableSerivce;;


@RestController
@RequestMapping("api/office-member/admin")
@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
public class ResponsableDeBureauController {

	//String name_, String surname_, int age_, String adresse_, String cin_,String image_link_, String username_, String password_, int office_id_
	
	 @Autowired
	 ResponsableSerivce responsabel;

	
		// get all OfficeAdmins
		@RequestMapping("/getAll")
		public List<ResponsableDeBureau> getAllMembers()
		{
			return this.responsabel.getAllMembers();
		}
		
		// create ResponsableDeBureau with post request
		@PostMapping("/{id_adresse}/{id_bureau}/create") 
		public boolean createOfficeAdmin(@PathVariable("id_adresse") int id_adresse,@PathVariable("id_bureau") int id_bureau, @RequestBody ResponsableDeBureau responsableDeBureau) throws Exception
		{
			return this.responsabel.createOfficeAdmin(id_adresse,id_bureau,responsableDeBureau);
	 	}
			  
		// modify ResponsableDeBureau // send with put request from client not post 
		@PutMapping("/update/{id}/{id_adresse}/{id_bureau}")
			  public boolean updateOfficeAdmin(@PathVariable("id") int id,@PathVariable("id_adresse") int id_adresse,@PathVariable("id_bureau") int id_bureau, @RequestBody ResponsableDeBureau responsableDeBureau) throws Exception {
			    return this.responsabel.updateOfficeAdmin(id,id_adresse,id_bureau, responsableDeBureau);
			  }
			  
		// delete ResponsableDeBureau
		@DeleteMapping("/delete/{id}")
		public boolean deleteAdmin(@PathVariable("id") int id) {
			return this.responsabel.deleteAdmin(id);
			
		}
		 
		// get single ResponsableDeBureau 
		
		@GetMapping("/get/{id}") 
		Optional<ResponsableDeBureau> getOneAdmin(@PathVariable int id) {
			return this.responsabel.getOneAdmin(id);
		}
		
		@PostMapping("/get-by-username") 
		public ResponsableDeBureau getElementByUsernameAndPassowrd(@RequestBody ResponsableDeBureau responsable) {					
			return this.responsabel.getElementByUsernameAndPassowrd(responsable.getUsername(), responsable.getPassword());
		}
		
	
		
}






















