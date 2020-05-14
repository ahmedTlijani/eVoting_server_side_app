package com.example.voting.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.voting.entities.Admin;
import com.example.voting.entities.Adresse;
import com.example.voting.entities.Parti;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.AdresseRepository;
import com.example.voting.repository.PartiRepository;

@Service
public class PartiService {

	
	@Autowired 
	 PartiRepository partiRepository;
	
	@Autowired
	 AdminRepository adminRepository;
	
	 @Autowired 
	 AdresseRepository adresseRepository;
	 
	
	public List<Parti> getAllPartis()
	{
		System.out.println("Get all parti...");
		ArrayList<Parti> partiList = new ArrayList<>();
		partiRepository.findAll().forEach(partiList::add);
		return partiList;
	}
	
	public boolean createParti(int admin_id,int id_adresse, Parti parti) throws Exception
	{
		System.out.println("Create parti...");
		// get admin with admin_id
		Set<Parti> partis = new HashSet<>();
        Admin admin1 = new Admin();
		
        Optional<Admin> byId = adminRepository.findById(admin_id);
        if (!byId.isPresent()) {
            throw new Exception("Author with id " + admin_id + " does not exist");
        }
        
        Admin admin = byId.get();
        
        Optional<Adresse> byIdadresse = adresseRepository.findById(id_adresse);
        if (!byId.isPresent()) {
            throw new Exception("adresse with id " + id_adresse + " does not exist");
        }
        
        Adresse adresse = byIdadresse.get();
        
        //Parti partiEntity = new Parti(parti.getName(), parti.getType(),parti.getImage());
		try {
			parti.setAdmin(admin);
			parti.setAdresse(adresse);
			partiRepository.save(parti);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
 	}
	
	  public boolean updateParti(int id,int id_adresse, Parti parti) throws Exception {
		    System.out.println("Update parti  with ID = " + id + "...");
		 
		    Optional<Parti> partiData = partiRepository.findById(id);
		    
		    Optional<Adresse> byId = adresseRepository.findById(id_adresse);
	        if (!byId.isPresent()) {
	            throw new Exception("adresse with id " + id_adresse + " does not exist");
	        }
	        
	        Adresse adresse = byId.get();
	        
	        
		    if (partiData.isPresent()) {
		      Parti _parti = partiData.get();
		      _parti.setName(parti.getName());
		      _parti.setType(parti.getType());
		      _parti.setAdresse(adresse);
		      _parti.setImage(parti.getImage());
		      try {
				partiRepository.save(_parti);
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
	  
	  public boolean deleteParti(int id) {
	 		try {
				partiRepository.deleteById(id);
				System.out.println("deleted...");
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("id n'existe pas...");
				return false;
			}
			
		}
	  
	 public  Optional<Parti> getOneParti(int id) {
		 Optional<Parti> p =  partiRepository.findById(id);
		 return p ;
		 // return null if not found
		}
	
	  
	  
	  
	  
	  
	
	
	
	
}
