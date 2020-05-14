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
import com.example.voting.entities.BureauElection;
import com.example.voting.entities.BureauElection;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.BureauElectionRepository;
import com.example.voting.services.BureauService;
import com.example.voting.repository.BureauElectionRepository;

@RestController
@RequestMapping("api/voting-office")
@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app

public class BureauElectionController {
	 
		@Autowired	
		BureauService bureau;
		
		// get all BureauElection from table BureauElection from db
		@RequestMapping("/getAll") 
		public List<BureauElection> getAllVotingOffices()
		{
			return this.bureau.getAllOffices();
		}
		
		
		// create BureauElection with post request
		@PostMapping("/{admin_id}/{adresse_id}/create") 
		public boolean createVotingOffice(@PathVariable("admin_id") int admin_id,@PathVariable("adresse_id") int adresse_id,@RequestBody  BureauElection bureauElection) throws Exception
		{
			 return this.bureau.createVotingOffice(admin_id,adresse_id, bureauElection);
	 	}
			  
		// modify BureauElection // send with put request from client not post 
		@PutMapping("/update/{id}/{adresse_id}")
			  public boolean updateVotingOffice(@PathVariable("id") int id,@PathVariable("adresse_id") int adresse_id, @RequestBody BureauElection bureauElection) throws Exception {
			
			    return this.bureau.updateVotingOffice(id,adresse_id, bureauElection);
			  }
			  
		// delete BureauElection
		@DeleteMapping("/delete/{id}")
		public boolean deleteOffice(@PathVariable("id") int id) {
			return this.bureau.deleteOffice(id);
		}
		 
		// get single BureauElection 
		
		@GetMapping("/get/{id}") 
		Optional<BureauElection> getOneVotingOffice(@PathVariable int id) {
			return this.bureau.getOneVotingOffice(id);
		}
		
		@GetMapping("/getByAdresseId/{id_adresse}") 
		BureauElection getOneVotingOfficeByAdresseId(@PathVariable int id_adresse) {
			return this.bureau.getOneVotingOfficeByAdresseId(id_adresse);
		}
		
		
	 
}
