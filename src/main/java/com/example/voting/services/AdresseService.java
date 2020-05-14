package com.example.voting.services;

import java.util.ArrayList;
import java.util.List;
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
public class AdresseService {

	 @Autowired 
	 AdresseRepository adresseRepository;
	 
	 
	 public List<Adresse> getAllAdresses()
		{
			System.out.println("Get all Adresse...");
			ArrayList<Adresse> adresseList = new ArrayList<>();
			adresseRepository.findAll().forEach(adresseList::add);
			System.out.println(adresseList);
			return adresseList;
		}
	 
	 public boolean createOneAdresse(Adresse adresse) throws Exception
		{
			System.out.println("Create PeopleDataSet...");
   
			try {
				adresseRepository.save(adresse);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	 	}
	 
	 public boolean updateOneAdresse(int id_adresse, Adresse adresse) throws Exception {
		    System.out.println("Update adresse  with ID = " + id_adresse + "...");
		 
		    Optional<Adresse> adresseData = adresseRepository.findById(id_adresse);

		    if (adresseData.isPresent()) {
		      Adresse _adresse_Set = adresseData.get();
		      _adresse_Set.setState(adresse.getState());
		      _adresse_Set.setProvince(adresse.getProvince());
		      _adresse_Set.setCode_postal(adresse.getCode_postal());
		      try {
				adresseRepository.save(_adresse_Set);
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
	 
	 	public boolean deleteOneAdresse(int id) {
			System.out.println("Delete adresse with ID = " + id + "...");
	 		try {
				adresseRepository.deleteById(id);
				System.out.println("deleted...");
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("id n'existe pas...");
				return false;
			}
			
		}
	 
	 public Optional<Adresse> getOneAdresse(int id) {
	 Optional<Adresse> p =  adresseRepository.findById(id);
	 return p ;
	 // return null if not found
	}
 
	 public List<Adresse> getProvinceByState(String state) {
		 List<Adresse> p =  adresseRepository.getProvinceByState(state);
		 return p ;
		 // return null if not found
		}
	 
	 public Adresse getAdresseId(String state, String province) {
		 Adresse p =  adresseRepository.getAdresseId(state, province);
		 return p ;
		 // return null if not found
		}
	 
	 public Adresse getCodePostal(int adresse_id) {
		 Adresse p =  adresseRepository.getCodePostal(adresse_id);
		 return p ;
		 // return null if not found
		}
	 
}
