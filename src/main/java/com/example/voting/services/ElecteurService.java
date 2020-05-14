package com.example.voting.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.voting.entities.Admin;
import com.example.voting.entities.BureauElection;
import com.example.voting.entities.Electeur;
import com.example.voting.entities.Parti;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.repository.AdminRepository;
import com.example.voting.repository.BureauElectionRepository;
import com.example.voting.repository.ElecteurRepository;
import com.example.voting.repository.PartiRepository;
import com.example.voting.repository.PeopleDataSetRepository;

@Service
public class ElecteurService {
	
	@Autowired 
	PeopleDataSetRepository peopleDataSetRepository;
	
	@Autowired
	ElecteurRepository electeurRepository;
	
	@Autowired
	BureauElectionRepository bureauRepository;
	
	@Autowired
	PeopleDataSetService peopleService;
	
	
	public List<Electeur> getAllElecteurs()
	{
		System.out.println("Get all Voters...");
		ArrayList<Electeur> voterList = new ArrayList<>();
		electeurRepository.findAll().forEach(voterList::add);
		return voterList;
	}
	
	public boolean createElecteur(int person_id,int id_bureau, Electeur electeur) throws Exception
	{
		System.out.println("Create Voter...");
		
		Optional<PeopleDataSet> byId = peopleDataSetRepository.findById(person_id);
        if (!byId.isPresent()) {
            throw new Exception("Author with id " + person_id + " does not exist");
        }
        
        PeopleDataSet person = byId.get();
        
        
        Optional<BureauElection> byIdB = bureauRepository.findById(id_bureau);
        if (!byIdB.isPresent()) {
            throw new Exception("Author with id " + id_bureau + " does not exist");
        }
        
        BureauElection bureau = byIdB.get();
        
        Electeur electeur2 = new Electeur(electeur.getCin());
        
		try {
			electeur2.setBureau(bureau);
			electeur2.setPerson(person);
			electeurRepository.save(electeur2);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
 	}
	
	  public boolean deleteElecteur(int id) {
			System.out.println("Delete Voter with ID = " + id + "...");
	 		try {
				electeurRepository.deleteById(id);
				System.out.println("deleted...");
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("id n'existe pas...");
				return false;
			}
			
		}
	  
	  
	  public Optional<Electeur> getOneElecteur(int id) {
			 Optional<Electeur> v =  electeurRepository.findById(id);
			 return v ;
			 // return null if not found
			}
	   
	  public Electeur getOneElecteurByCin(String cin) {
			 Electeur v =  electeurRepository.getElementByCin(cin);
			 return v ;
			 // return null if not found
			}
	  
	  public Map<String, Object> getOneElecteurByCinDate(String cin, Date date_naissance) throws ParseException {					
			
			HashMap<String, Object> map = new HashMap<>();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("-------------------------");
			String date_ = dateFormat.format(date_naissance);
			System.out.println("date de naissance from client " + date_);
			//System.out.println("******************************");
			if(this.getOneElecteurByCin(cin)!= null) // 
			{
				Electeur e = null;
				try {
					e = this.getOneElecteurByCin(cin);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("bureai " + e);
				PeopleDataSet p = this.peopleService.getOnePeopleByCin(cin);
				
				//System.out.println("-------------------------");
				System.out.println("date de naissance from db " + dateFormat.format(p.getDate_naissance()));
				if(date_.equals(dateFormat.format(p.getDate_naissance()))) // cin et date de naissance compatible est inscrit
				{
					map.put("data", e);
					map.put("status", true);
					return map;		
				}
				else { // cin et date de naissance n'est pas compatible
					map.put("data", null);
					map.put("status", false);
					return map;
				}    
			}
			return null;
		}
	  
	
	
	
	
}
