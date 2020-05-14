package com.example.voting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.voting.entities.Admin;
import com.example.voting.entities.Adresse;
import com.example.voting.entities.BureauElection;
import com.example.voting.entities.ResponsableDeBureau;
import com.example.voting.repository.AdresseRepository;
import com.example.voting.repository.BureauElectionRepository;
import com.example.voting.repository.ResponsableDeBureauRepository;

@Service
public class ResponsableSerivce {

	@Autowired 
	 ResponsableDeBureauRepository adminsRepository;
	
	@Autowired 
	 AdresseRepository adresseRepository;
	
	@Autowired
	BureauElectionRepository bureauRepository;
	
		public List<ResponsableDeBureau> getAllMembers()
			{
				System.out.println("Get all members...");
				ArrayList<ResponsableDeBureau> adminiList = new ArrayList<>();
				adminsRepository.findAll().forEach(adminiList::add);
				return adminiList;
			}
			
			public boolean createOfficeAdmin(int id_adresse,int id_bureau, ResponsableDeBureau responsableDeBureau) throws Exception
			{
				System.out.println("Create ResponsableDeBureau...");
				
				Optional<Adresse> byId = adresseRepository.findById(id_adresse);
		        if (!byId.isPresent()) {
		            throw new Exception("adresse with id " + id_adresse + " does not exist");
		        }
		        
		        Adresse adresse = byId.get();
		        
		        
		        Optional<BureauElection> byIdB = bureauRepository.findById(id_bureau);
		        if (!byIdB.isPresent()) {
		            throw new Exception("Author with id " + id_bureau + " does not exist");
		        }
		        
		        BureauElection bureau = byIdB.get();
		        
		        
				//ResponsableDeBureau officeAdminEntity = new ResponsableDeBureau(responsableDeBureau.getName(),  responsableDeBureau.getSurname(),responsableDeBureau.getGender(),responsableDeBureau.getCin(),responsableDeBureau.getDate_naissance(),responsableDeBureau.getImage(),responsableDeBureau.getUsername(), responsableDeBureau.getPassword());
				
		        try {
		        	responsableDeBureau.setBureau(bureau);
		        	responsableDeBureau.setAdresse(adresse);
					adminsRepository.save(responsableDeBureau);
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		 	}
				  
			// modify ResponsableDeBureau // send with put request from client not post 
				  public boolean updateOfficeAdmin(int id,int id_adresse,int id_bureau, ResponsableDeBureau responsableDeBureau) throws Exception {
				    System.out.println("Update ResponsableDeBureau  with ID = " + id + "...");
				 
				    Optional<Adresse> byId = adresseRepository.findById(id_adresse);
			        if (!byId.isPresent()) {
			            throw new Exception("adresse with id " + id_adresse + " does not exist");
			        }
			        
			        Adresse adresse = byId.get();
			        
			        Optional<BureauElection> byIdB = bureauRepository.findById(id_bureau);
			        if (!byIdB.isPresent()) {
			            throw new Exception("Author with id " + id_bureau + " does not exist");
			        }
			        
			        BureauElection bureau = byIdB.get();
			        
			        
				    Optional<ResponsableDeBureau> officeAdminData = adminsRepository.findById(id);
				 
				    if (officeAdminData.isPresent()) {
				      ResponsableDeBureau _officeAdmin = officeAdminData.get();
				      _officeAdmin.setName(responsableDeBureau.getName());
				      _officeAdmin.setSurname(responsableDeBureau.getSurname());
				      _officeAdmin.setGender(responsableDeBureau.getGender());
				      _officeAdmin.setAdresse(adresse);
				      _officeAdmin.setBureau(bureau);
				      _officeAdmin.setCin(responsableDeBureau.getCin());
				      _officeAdmin.setDate_naissance(responsableDeBureau.getDate_naissance());
				      _officeAdmin.setUsername(responsableDeBureau.getUsername());
				      _officeAdmin.setPassword(responsableDeBureau.getPassword());
				      _officeAdmin.setImage(responsableDeBureau.getImage());

				      try {
				    	
						adminsRepository.save(_officeAdmin);
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
				  
			// delete ResponsableDeBureau
			public boolean deleteAdmin(int id) {
				System.out.println("Delete ResponsableDeBureau with ID = " + id + "...");
		 		try {
					adminsRepository.deleteById(id);
					System.out.println("deleted...");
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("id n'existe pas...");
					return false;
				}
				
			}
			 
			// get single ResponsableDeBureau 
			
			public Optional<ResponsableDeBureau> getOneAdmin(int id) {
			 Optional<ResponsableDeBureau> p =  adminsRepository.findById(id);
			 return p ;
			 // return null if not found
			}
			
			
			public ResponsableDeBureau getElementByUsernameAndPassowrd(String username, String password)
			{
				System.out.println("return admin with username and password " + username + " " + password);
				ResponsableDeBureau record = adminsRepository.getElementByUsernameAndPassowrd(username, password);
			    return record;
			}
			
	
	
}
