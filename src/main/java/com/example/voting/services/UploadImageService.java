package com.example.voting.services;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.voting.entities.Admin;
import com.example.voting.entities.Parti;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.repository.AdminRepository;

import ch.qos.logback.core.Context;

@Service
public class UploadImageService {

	@Autowired
	ServletContext context;
	
	String baseUplaodUrl="D:\\Ahmed\\esip\\BLOCK-CHAIN\\public_\\image_uplaod_\\";
	String baseGetUrl="http://localhost/images_/";
	
	// uplaod image 
	public Map<String, String> uplaodImage(String cin, String base64image) {
			System.out.println(cin);
			System.out.println(base64image);
			// save image and return image src
			String result="";
	        try
	        {
	            //This will decode the String which is encoded by using Base64 class
	            byte[] imageByte=Base64.getDecoder().decode(base64image);
	            	            
	            //new FileOutputStream(this.baseUplaodUrl).write(imageByte);
	            String path=this.baseUplaodUrl+cin+".jpg";
	            Path file = Paths.get(path);
	            Files.write(file, imageByte);
	            
	            String path_image = this.baseGetUrl + cin + ".jpg";
	            
	            System.out.println(path_image);
	            System.out.println("success ");
	            result = path_image;
	        }
	        catch(Exception e)
	        {
	           System.out.println(e);
	        }
	        
	        HashMap<String, String> map = new HashMap<>();
	        map.put("url", result);
	        return map;
	        			
		}

	// get image link by cin 
	public String getIMageByCin(String cin)
	{
		return "link";
	}
	
	
}
