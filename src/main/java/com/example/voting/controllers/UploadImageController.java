package com.example.voting.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
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
import com.example.voting.services.UploadImageService;
 


@RestController
@RequestMapping("api/image")
@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
public class UploadImageController {
		
		@Autowired
		UploadImageService imageService;
	
		// get all People
		@RequestMapping("/upload/{cin}")
		public Map<String, String> uploadImage(@PathVariable String cin, @RequestBody String base64image)
		{
			return this.imageService.uplaodImage(cin, base64image);
		}
		
		
}






















