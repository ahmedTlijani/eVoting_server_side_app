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
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private int admin_id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;

	// one to many with parti
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private Set<Parti> partis = new HashSet<>();
	
	// one to many with BureauEelection
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private Set<BureauElection> bureaux = new HashSet<>();
	
	
	
	public Admin() {}
	
	public Admin(int admin_id, String username, String password) {
		super();
		this.admin_id = admin_id;
		this.username = username;
		this.password = password;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
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
	
			
	

	
	
}
