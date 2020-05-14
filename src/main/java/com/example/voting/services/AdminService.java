package com.example.voting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.voting.entities.Admin;
import com.example.voting.entities.Parti;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	
	public List<Admin> getAdmins() {
		System.out.println("Get all admins...");
		ArrayList<Admin> adminList = new ArrayList<>();
		adminRepository.findAll().forEach(adminList::add);
		return adminList;
    }
	
	
	public Admin getElementByUsernameAndPassowrd(String username, String password)
	{
			System.out.println("return admin with username and password " + username + " " + password);
			Admin record = adminRepository.getElementByUsernameAndPassowrd(username, password);
			System.out.println(record);
			return record;
	
	}
	
	 public  Optional<Admin> getOneAdminById(int id) {
		 Optional<Admin> p =  adminRepository.findById(id);
		 return p ;
		 // return null if not found
		}
	 
	 
	
}
