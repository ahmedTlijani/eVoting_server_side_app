package com.example.voting.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.voting.entities.Admin;
import com.example.voting.entities.Adresse;
import com.example.voting.entities.BureauElection;
import com.example.voting.entities.Parti;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.AdresseRepository;
import com.example.voting.repository.BureauElectionRepository;
import com.example.voting.repository.PartiRepository;

@Service
public class BureauService {
	
	@Autowired 
	BureauElectionRepository officeRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	 @Autowired 
	 AdresseRepository adresseRepository;
	
	public List<BureauElection> getAllOffices()
	{
		System.out.println("Get all Office...");
		ArrayList<BureauElection> officeList = new ArrayList<>();
		officeRepository.findAll().forEach(officeList::add);
		return officeList;
	}
	
	public boolean createVotingOffice(int admin_id, int id_adresse, BureauElection bureauElection) throws Exception
	{
		System.out.println("Create BureauElection...");
		
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
        
		try {
			bureauElection.setAdresse(adresse);
			bureauElection.setAdmin(admin);
			bureauElection.setCreation_date(new Date());
			officeRepository.save(bureauElection);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
 	}
	
	

	  public boolean updateVotingOffice(int id,int id_adresse,BureauElection bureauElection) throws Exception {
	    System.out.println("Update BureauElection  with ID = " + id + "...");
	 
	    Optional<Adresse> byId = adresseRepository.findById(id_adresse);
        if (!byId.isPresent()) {
            throw new Exception("adresse with id " + id_adresse + " does not exist");
        }
        
        Adresse adresse = byId.get();
        
        
	    Optional<BureauElection> votingOfficeData = officeRepository.findById(id);
	 
	    
	    if (votingOfficeData.isPresent()) {
	      BureauElection _votingOffice = votingOfficeData.get();
	      _votingOffice.setName(bureauElection.getName());
 	      _votingOffice.setMax_number_voter(bureauElection.getMax_number_voter());
 	      _votingOffice.setAdresse(adresse);
		      try {
				officeRepository.save(_votingOffice);
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
	  
	  public boolean deleteOffice(int id) {
			System.out.println("Delete BureauElection with ID = " + id + "...");
	 		try {
				officeRepository.deleteById(id);
				System.out.println("deleted...");
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("id n'existe pas...");
				return false;
			}
			
		}
	  
	  
	  public Optional<BureauElection> getOneVotingOffice(int id) {
			 Optional<BureauElection> v =  officeRepository.findById(id);
			 return v ;
			 // return null if not found
			}
	  
	  
	  
	  // gestion des responasable ici + gestion des electeur
	  
	  public BureauElection getOneVotingOfficeByAdresseId(int id) {
			 BureauElection v =  officeRepository.getOneVotingOfficeByAdresseId(id);
			 return v ;
			 // return null if not found
			}
	  
	  
	
	
}
