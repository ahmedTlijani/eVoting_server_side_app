package com.example.voting.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class Personne {
	
	
	@Column(nullable = false)
    private String name;
 
	@Column(nullable = false)
    private String surname;
 
	@Column(nullable = false) // gender
    private String gender;
 
	// adresse 
	@OneToOne
    @JoinColumn(name="adresse")
    private Adresse adresse;
 
	@Column(nullable = false)
    private String cin;
 
	@Column(nullable = false)
    private Date date_naissance;
	
	@Column(nullable = false)
    private String image;

	
	
	
	public Personne(String name, String surname, String gender, String cin,
			Date date_naissance, String image) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.cin = cin;
		this.date_naissance = date_naissance;
		this.image = image;
	}

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	
	
}
