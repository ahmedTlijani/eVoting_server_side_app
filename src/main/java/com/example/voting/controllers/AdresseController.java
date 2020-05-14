package com.example.voting.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.voting.entities.Parti;
import com.example.voting.entities.Adresse;
import com.example.voting.entities.BureauElection;
import com.example.voting.entities.BureauElection;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.BureauElectionRepository;
import com.example.voting.services.AdresseService;
import com.example.voting.services.BureauService;
import com.example.voting.repository.BureauElectionRepository;

@RestController
@RequestMapping("api/adresse")
@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
public class AdresseController {
	 
		@Autowired
		AdresseService adresseService;
		
		// get all adresse from table BureauElection from db
		@RequestMapping("/getAll") 
		public List<Adresse> getAllVotingOffices()
		{
			return this.adresseService.getAllAdresses();
		}
		
		
		@PostMapping("/create") 
		public boolean createAdresse(@RequestBody  Adresse adresse) throws Exception
		{
			 return this.adresseService.createOneAdresse(adresse);
	 	}
			  
		// modify adresse // send with put request from client not post 
		@PutMapping("/update/{id_adresse}")
			  public boolean updateAdresse(@PathVariable("id_adresse") int id_adresse, @RequestBody Adresse adresse) throws Exception {
			
			    return this.adresseService.updateOneAdresse(id_adresse, adresse);
			  }
			  
		// delete adresse
		@DeleteMapping("/delete/{id}")
		public boolean deleteAdresse(@PathVariable("id") int id) {
			return this.adresseService.deleteOneAdresse(id);
		}
		 
		// get single adresse 
		
		@GetMapping("/get/{id}") 
		Optional<Adresse> getOneAdress(@PathVariable int id) {
			return this.adresseService.getOneAdresse(id);
		}
		
		// get provinces by states
		
		@GetMapping("/get-by-state/{state}") 
		List<Adresse> getProvinceByState(@PathVariable String state) {
			return this.adresseService.getProvinceByState(state);
		}
		
		// get adresse by state and province
		
		@GetMapping("/get-adresse-id/{state}/{province}") 
		Adresse getAdresseId(@PathVariable String state, @PathVariable String province) {
			return this.adresseService.getAdresseId(state, province);
		}
		
		// get code postal by id adresse
		
		@GetMapping("/get-code-postal/{adresse_id}") 
		Adresse getCodePostal(@PathVariable int adresse_id) {
			return this.adresseService.getCodePostal(adresse_id);
		}
		
	 
}
