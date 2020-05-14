package com.example.voting.entities;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "electeur")
public class Electeur  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_electeur;
	
	// add annoation for 1 to 1 with PeopleDataSet
    
	@Column(nullable = false)
	private String cin;
	
	@Column
	private String hash_code;
	
	//http://websystique.com/hibernate/hibernate-one-to-one-unidirectional-with-foreign-key-associations-annotation-example/
	@OneToOne
    @JoinColumn(name="person_information")
    private PeopleDataSet person;
	
	// bureauelection 
	@ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BureauElection bureau;
	
	
	
	public Electeur(String cin) {
		super();
		this.cin = cin;
		this.setHash_code(this.createHash_code_());
	}

	
	public Electeur() {}

	public String createHash_code_()
	{
		// get current date to create the hash from the name + cin + and current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String code = this.getCin() + dateFormat.format(new Date()).toString() ;
		// create the 256 hash
		String sha256hex = DigestUtils.sha256Hex(code);		
		return sha256hex;
	}
	
	public void setHash_code(String hash_code_) {
		
		this.hash_code = hash_code_;
	}

	public String getHash_code() {
		return hash_code;
	}


	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}


	public int getId_electeur() {
		return id_electeur;
	}

	

	public void setId_electeur(int id_electeur) {
		this.id_electeur = id_electeur;
	}



	public PeopleDataSet getPerson() {
		return person;
	}

	public void setPerson(PeopleDataSet person) {
		this.person = person;
	}



	public BureauElection getBureau() {
		return bureau;
	}



	public void setBureau(BureauElection bureau) {
		this.bureau = bureau;
	}

	
	
	
	
	
	
	
}
