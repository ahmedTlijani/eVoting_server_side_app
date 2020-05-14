package com.example.voting.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private int adresse_id;
	
	@Column(nullable = false)
    private String state;
 
	@Column(nullable = false)
    private String province;
	

	@Column(nullable = false)
    private String code_postal;

	// one to many with peopleDataSet
	@OneToMany(mappedBy = "adresse", fetch = FetchType.LAZY)
    private Set<PeopleDataSet> people = new HashSet<>();
	
	
	
	public Adresse() {}
	
	public Adresse(String state, String province, String code_postal) {
		super();
		this.state = state;
		this.province = province;
		this.code_postal = code_postal;
	}

	public int getAdresse_id() {
		return adresse_id;
	}


	public void setAdresse_id(int adresse_id) {
		this.adresse_id = adresse_id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCode_postal() {
		return code_postal;
	}


	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	
	
	
}
