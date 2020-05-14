package com.example.voting.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.voting.entities.Admin;
import com.example.voting.services.AdminService;
import com.example.voting.services.FacialVerificaitonService;
import com.example.voting.services.UploadImageService;
 


@RestController
@RequestMapping("api/verify-face")
//@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
@CrossOrigin() 

public class FacialVerificationController {
		
		
		@Autowired
		FacialVerificaitonService facialService;
	
		// get all People
		@RequestMapping("/verify/{cin}")
		public String verifierInscription(@PathVariable("cin") String cin,@RequestBody String data) throws Exception
		{
			return this.facialService.verifierInscription(cin,data);
		}
		
		
}






















