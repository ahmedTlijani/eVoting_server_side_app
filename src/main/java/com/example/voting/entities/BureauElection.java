package com.example.voting.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bureau")
public class BureauElection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private int office_id;
	
	@Column(nullable = false)
	private String name ;
	
	// adresse 
	@OneToOne
    @JoinColumn(name="adresse")
    private Adresse adresse;
	
	@Column(nullable = false)
	private int max_number_voter;
	
	@Column
	private Date creation_date ;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Admin admin;
	
	// one to many with electeur
	@OneToMany(mappedBy = "bureau", fetch = FetchType.LAZY)
	private Set<Electeur> electeurs = new HashSet<>();
		

	
	public BureauElection(String name, int max_number_voter) {
		super();
		this.name = name;
		this.max_number_voter = max_number_voter;
	}

	public BureauElection() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public int getMax_number_voter() {
		return max_number_voter;
	}
	
	public void setMax_number_voter(int max_number_voter) {
		this.max_number_voter = max_number_voter;
	}
	
	
	//getter method to retrieve the AuthorId
    public int getAdmin_id(){
        return this.admin.getAdmin_id();
    }

    @JsonIgnore
    public Admin getAdmin() {
        return this.admin;
    }

    @JsonIgnore
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public int getOffice_id() {
		return office_id;
	}

	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

    
    
	
	
	
}
