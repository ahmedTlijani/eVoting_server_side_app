package com.example.voting.entities;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */
@Entity
public class PeopleDataSet extends Personne{

 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_personne_;

	
	public PeopleDataSet(String name, String surname, String gender, String cin,
			Date date_naissance, String image) {
		super(name, surname, gender,cin, date_naissance, image);

	}

	
	public PeopleDataSet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId_personne_() {
		return id_personne_;
	}

	public void setId_personne_(int id_personne_) {
		this.id_personne_ = id_personne_;
	}
 
	
	
	
	

}