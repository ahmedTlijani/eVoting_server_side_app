package com.example.voting.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import com.example.voting.entities.PeopleDataSet;
import com.example.voting.repository.PeopleDataSetRepository;
import com.example.voting.services.PeopleDataSetService;
import com.example.voting.repository.PeopleDataSetRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@RestController
@RequestMapping("api/people-data-set/")
@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
public class PeopleDataSetController {

		@Autowired
		PeopleDataSetService peopleService;
	
		// get all People
		@RequestMapping("/getAll")
		public List<PeopleDataSet> getAllMembers()
		{
			return this.peopleService.getAllMembers();
		}
		
		// create PeopleDataSet with post request
		@PostMapping("/{id_adresse}/create") 
		public boolean createOnePeople(@PathVariable("id_adresse") int id_adresse,@RequestBody PeopleDataSet peopleDataSet) throws Exception
		{
			return this.peopleService.createOnePeople(id_adresse,peopleDataSet);
	 	}
			  
		// modify PeopleDataSet // send with put request from client not post 
		@PutMapping("/update/{id}/{id_adresse}")
			  public boolean updateOnePeople(@PathVariable("id") int id,@PathVariable("id_adresse") int id_adresse, @RequestBody PeopleDataSet peopleDataSet) throws Exception {
			    return this.peopleService.updateOnePeople(id,id_adresse, peopleDataSet);	    
			  }
			  
		// delete PeopleDataSet
		@DeleteMapping("/delete/{id}")
		public boolean deleteOnePeople(@PathVariable("id") int id) {
			return this.peopleService.deleteOnePeople(id);
		}
		 
		// get single PeopleDataSet 
		
		@GetMapping("/get/{id}") 
		Optional<PeopleDataSet> getOnePeople(@PathVariable int id) {
			return this.peopleService.getOnePeople(id);
		}
	
		
		// get element by cin 
		
		@GetMapping("/get-by-cin/{cin}") 
		public PeopleDataSet getOnePeopleByCin(@PathVariable String cin) {					
			return this.peopleService.getOnePeopleByCin(cin);
		}
		
		// get element by cin and date de naissance
		@PostMapping("/get-by-cin-date") 
		public Map<String, Object> getOnePeopleByCinDate(@RequestBody PeopleDataSet peopleDataSet) throws ParseException {					
			return this.peopleService.getOnePeopleByCinDate(peopleDataSet.getCin() , peopleDataSet.getDate_naissance());
		}
		
		
		
}






















