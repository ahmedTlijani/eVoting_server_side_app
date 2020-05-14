package com.example.voting.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ResponsableDeBureau extends Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_responsable;
 
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;

	// adresse 
	@OneToOne
    @JoinColumn(name="bureau_id")
    private BureauElection bureau;
	
	
	public ResponsableDeBureau(String name, String surname, String gender,String cin,
			Date date_naissance, String image, String username, String password) {
		super(name, surname, gender,cin, date_naissance, image);
		this.username = username;
		this.password = password;
	}

	
	
	public ResponsableDeBureau() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId_responsable() {
		return id_responsable;
	}

	public void setId_responsable(int id_responsable) {
		this.id_responsable = id_responsable;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public BureauElection getBureau() {
		return bureau;
	}



	public void setBureau(BureauElection bureau) {
		this.bureau = bureau;
	}
	
	
	
	

}
