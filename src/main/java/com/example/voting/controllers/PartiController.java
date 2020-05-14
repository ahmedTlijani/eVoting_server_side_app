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

import com.example.voting.entities.ResponsableDeBureau;
import com.example.voting.entities.Parti;
import com.example.voting.entities.Electeur;
import com.example.voting.repository.PartiRepository;
import com.example.voting.services.PartiService;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.ElecteurRepository;;


@RestController
@RequestMapping("api/parti")
//@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
@CrossOrigin() // cross origin angular app

public class PartiController {

		@Autowired
		PartiService partiService;
		
		// get all partis
		@RequestMapping("/getAll")
		public List<Parti> getAllPartis()
		{
			return partiService.getAllPartis();
		}
		
				
		// create parti with post request
		@PostMapping("/{admin_id}/{adresse_id}/create") 
		public boolean createParti(@PathVariable("admin_id") int admin_id,@PathVariable("adresse_id") int adresse_id, @RequestBody Parti parti) throws Exception
		{
			return partiService.createParti(admin_id,adresse_id, parti);
	 	}
			  
		// modify parti // send with put request from client not post 
		@PutMapping("/update/{id}/{adresse_id}")
		public boolean updateParti(@PathVariable("id") int id,@PathVariable("adresse_id") int adresse_id, @RequestBody Parti parti) throws Exception {
			return this.partiService.updateParti(id,adresse_id, parti);		    
		}
			  
		// delete parti
		@DeleteMapping("/delete/{id}")
		public boolean deleteParti(@PathVariable("id") int id) {
			System.out.println("Delete parti with ID = " + id + "...");
	 		return this.partiService.deleteParti(id);
			
		}
		 
		// get single parti 
		
		@GetMapping("/get/{id}") 
		Optional<Parti> getOneParti(@PathVariable int id) {
		 return this.partiService.getOneParti(id);
		}
	
	
	
	
	
}




















