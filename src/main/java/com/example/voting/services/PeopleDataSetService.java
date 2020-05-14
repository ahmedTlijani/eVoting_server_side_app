package com.example.voting.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.voting.entities.Admin;
import com.example.voting.entities.Adresse;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.repository.AdresseRepository;
import com.example.voting.repository.PeopleDataSetRepository;

@Service
public class PeopleDataSetService {

	

	 @Autowired 
	 PeopleDataSetRepository peopleRepository;
	 
	 @Autowired 
	 AdresseRepository adresseRepository;
	 
	 @Autowired 
	 ElecteurService electeurService;
	 
	 
	 
	int legalage= 18;
	 
	 public List<PeopleDataSet> getAllMembers()
		{
			System.out.println("Get all members...");
			ArrayList<PeopleDataSet> peoplelist = new ArrayList<>();
			peopleRepository.findAll().forEach(peoplelist::add);
			return peoplelist;
		}
	 
	 public boolean createOnePeople(int id_adresse, PeopleDataSet peopleDataSet) throws Exception
		{
			System.out.println("Create PeopleDataSet...");
			
			Optional<Adresse> byId = adresseRepository.findById(id_adresse);
	        if (!byId.isPresent()) {
	            throw new Exception("adresse with id " + id_adresse + " does not exist");
	        }
	        
	        Adresse adresse = byId.get();
	        
			PeopleDataSet peopleEntity = new PeopleDataSet(peopleDataSet.getName(), peopleDataSet.getSurname(),
					peopleDataSet.getGender(), peopleDataSet.getCin(),peopleDataSet.getDate_naissance(),peopleDataSet.getImage()
					);
			try {
				peopleEntity.setAdresse(adresse);
				peopleRepository.save(peopleEntity);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	 	}
	 
	 public boolean updateOnePeople(int id,int id_adresse, PeopleDataSet peopleDataSet) throws Exception {
		    System.out.println("Update PeopleDataSet  with ID = " + id + "...");
		 
		    Optional<PeopleDataSet> peopleData = peopleRepository.findById(id);
		 
		    Optional<Adresse> byId = adresseRepository.findById(id_adresse);
	        if (!byId.isPresent()) {
	            throw new Exception("adresse with id " + id_adresse + " does not exist");
	        }
	        
	        Adresse adresse = byId.get();
	        
		    if (peopleData.isPresent()) {
		      PeopleDataSet _peopleSet = peopleData.get();
		      _peopleSet.setName(peopleDataSet.getName());
		      _peopleSet.setSurname(peopleDataSet.getSurname());
		      _peopleSet.setGender(peopleDataSet.getGender());
		      _peopleSet.setAdresse(adresse);
		      _peopleSet.setCin(peopleDataSet.getCin());
		      _peopleSet.setDate_naissance(peopleDataSet.getDate_naissance());
		      _peopleSet.setImage(peopleDataSet.getImage());
		      try {
				peopleRepository.save(_peopleSet);
				return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}     
		    } else {
		      return false;
		    }
		    
		  }
	 
	 public boolean deleteOnePeople(int id) {
			System.out.println("Delete PeopleDataSet with ID = " + id + "...");
	 		try {
				peopleRepository.deleteById(id);
				System.out.println("deleted...");
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("id n'existe pas...");
				return false;
			}
			
		}
	 
	 public Optional<PeopleDataSet> getOnePeople(int id) {
	 Optional<PeopleDataSet> p =  peopleRepository.findById(id);
	 return p ;
	 // return null if not found
	}

	 public PeopleDataSet getOnePeopleByCin(String cin) {					
		//System.out.println("return cin " + cin);
	    PeopleDataSet records = peopleRepository.getElementByCin(cin);
	    return records;
	}
	 
	 public Map<String, Object> getOnePeopleByCinDate(String cin, Date date_naissance) throws ParseException {					
			System.out.println("return cin date " + cin + " " + date_naissance);
			// convert date from client to mysql date format : yyyy-MM-dd HH:mm:ss
			
			HashMap<String, Object> map = new HashMap<>();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("-------------------------");
			String date_ = dateFormat.format(date_naissance);
			System.out.println("date de naissance from client " + date_);
			//System.out.println("******************************");
			if(this.getOnePeopleByCin(cin)!= null) // 
			{
				System.out.println("Age: " + this.calculateAge(date_));
				PeopleDataSet p = this.getOnePeopleByCin(cin);
				
				//System.out.println("-------------------------");
				System.out.println("date de naissance from db " + dateFormat.format(p.getDate_naissance()));
				if(date_.equals(dateFormat.format(p.getDate_naissance()))) // cin et date de naissance compatible and age >=18 and n"est pas inscrit
				{
					// check if inscrit ou nn
					
					if(this.electeurService.getOneElecteurByCin(cin) == null) // n'est pas inscrit
					{
						if(this.calculateAge(date_) >= this.legalage )
						{
							map.put("data", p);
							map.put("status", "OK");
							return map;
						}
						else
						{
							map.put("data", null);
							map.put("status", "FAIL-age");
							return map;
						}
					}
					else
					{
						map.put("data", null);
						map.put("status", "FAIL-inscrit");
						return map;
					}
					
					
				}
				else { // cin et date de naissance n'est pas compatible
					map.put("data", null);
					map.put("status", "FAIL-date");
					return map;
				}    
			}
			return null;
		}
	 
	 
	
	public int calculateAge(String date_) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date_birth = LocalDate.parse(date_, formatter);
		//System.out.println("birthdate: "+ date_birth);
		LocalDate today = LocalDate.now(); //Today's date
		//System.out.println(today + " " + date_birth);
        return Period.between(date_birth, today).getYears();
    }
   
	 
 
	
}
