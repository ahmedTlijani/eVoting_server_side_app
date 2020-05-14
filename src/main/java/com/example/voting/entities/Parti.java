package com.example.voting.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Parti {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int parti_id;
		
		@Column(nullable = false)
		private String name;
		
		@Column(nullable = false)
		private String type; // Independent / Coalition / party - حزبية أو ائتلافية أو مستقلة
		
		// adresse 
		@OneToOne
	    @JoinColumn(name="adresse")
	    private Adresse adresse;
		
		@Column(nullable = false)
		private String image; // image 
		
		
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "adminId", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private Admin admin;
		
		
		
		public String getName() {
			return name;
		}
		
		
		
		public Parti(String name, String type, String image) {
			super();
			this.name = name;
			this.type = type;
 			this.image = image;
		}

		public Parti() {}

		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		
		
		
		// get admin information //https://medium.com/skillhive/how-to-retrieve-a-parent-field-from-a-child-entity-in-a-one-to-many-bidirectional-jpa-relationship-4b3cd707bfb7
		
		 //getter method to retrieve the AuthorId
	    public int getAdmin_id(){
	        return this.admin.getAdmin_id();
	    }

	    @JsonIgnore
	    public Admin getAuthor() {
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



		public String getImage() {
			return image;
		}



		public void setImage(String image) {
			this.image = image;
		}



		public int getParti_id() {
			return parti_id;
		}

		public void setParti_id(int parti_id) {
			this.parti_id = parti_id;
		}

	    
		
		
}
