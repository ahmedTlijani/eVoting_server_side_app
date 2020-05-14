package com.example.voting.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.entities.BureauElection;
import com.example.voting.entities.Electeur;
import com.example.voting.entities.BureauElection;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.BureauElectionRepository;
import com.example.voting.services.BureauService;
import com.example.voting.services.ElecteurService;
import com.example.voting.repository.BureauElectionRepository;

@RestController
@RequestMapping("api/voter")
//@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8200"}) // cross origin angular app - and ionic app
@CrossOrigin()

public class ElecteurController {
	 
		@Autowired
		ElecteurService electeurService;
		
		// get all Voter from table electeur from db
		@RequestMapping("/getAll") 
		public List<Electeur> getAllVotingOffices()
		{
			return this.electeurService.getAllElecteurs();
		}
		
		
		// create Voter with post request
		@PostMapping("/{person_id}/{id_bureau}/create") 
		public boolean createVoter(@PathVariable("person_id") int person_id,@PathVariable("id_bureau") int id_bureau,@RequestBody Electeur electeur) throws Exception
		{
			 return this.electeurService.createElecteur(person_id,id_bureau, electeur);
	 	}
			  

		// delete Voter
		@DeleteMapping("/delete/{id}")
		public boolean deleteVoter(@PathVariable("id") int id) {
			return this.electeurService.deleteElecteur(id);
		}
		 
		// get single Voter 
		
		@GetMapping("/get/{id}") 
		Optional<Electeur> getOneVoter(@PathVariable int id) {
			return this.electeurService.getOneElecteur(id);
		}
		
		@GetMapping("/get-by-cin/{cin}") 
		Electeur getOneVoterByCin(@PathVariable String cin) {
			return this.electeurService.getOneElecteurByCin(cin);
		}
		
		
		@PostMapping("/get-by-cin-date/{cin}") 
		 Map<String, Object> getOneElecteurByCinDate(@PathVariable String cin,@RequestBody PeopleDataSet people ) throws ParseException {
			return this.electeurService.getOneElecteurByCinDate(cin, people.getDate_naissance());
		}
	 
}
