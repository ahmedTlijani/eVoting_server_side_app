package com.example.voting.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.voting.entities.Admin;
import com.example.voting.entities.Parti;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.repository.AdminRepository;

import ch.qos.logback.core.Context;

@Service
public class FacialVerificaitonService {
		 
	/* Python api 
	 * http://127.0.0.1:5000/api/verify-face/
	 * let data = { cin : , known_image : , unknown_image: }
	 * known_image and unknown_image are base64 images
	 * 
	 * Python return response
	 * data = { 'status'  : '', 'message' :'' }
	 *  OK FAIL 0
	 * 
	 * */
	
	private String python_url= "http://127.0.0.1:5000/api/verify-face/";
	
	String image_type="jpg";
	
	@Autowired 
	PeopleDataSetService peopleService;
	
	
	public String verifierInscription(String cin, String data) throws Exception
	{
		PeopleDataSet p;
		if(this.peopleService.getOnePeopleByCin(cin)!= null) // 
		{
			p = this.peopleService.getOnePeopleByCin(cin);			
		}else return null;
		
		BufferedImage img = ImageIO.read(new URL(p.getImage())); // get image from link and convert it to base64
		byte[] imageInByte = this.imageToByteArray(img);
		String base64_out = Base64.getEncoder().encodeToString(imageInByte);
		//System.out.println("-----------------------------------");
		//System.out.println(base64_out);
		//System.out.println(o);
		return compareFaces(cin,base64_out, data);
		
	}
	
	public String compareFaces(String cin, String known_image, String unknown_image) throws Exception
	{
		//  send the post request
		return (this.sendPost(cin, known_image, unknown_image));
	}
	
	
	public byte[] imageToByteArray(BufferedImage image) {
	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    try {
	        ImageIO.write(image, this.image_type , stream);
	    } catch(IOException e) {
	        // This *shouldn't* happen with a ByteArrayOutputStream, but if it
	        // somehow does happen, then we don't want to just ignore it
	        throw new RuntimeException(e);
	    }
	    return stream.toByteArray();
	    // ByteArrayOutputStreams don't need to be closed (the documentation says so)
	}
	
	// HTTP POST request
	 public String sendPost(String cin, String known_image, String unknown_image) throws Exception {
				
		 JSONObject obj = new JSONObject();
		 obj.put("cin", cin);
		 obj.put("known_image", known_image);
		 obj.put("unknown_image", unknown_image);
				
		 String payload = obj.toString();
				
		 //System.out.println("--------------------------");
		 //System.out.println(payload);
				
		 StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON); //APPLICATION_FORM_URLENCODED
		 HttpClient httpClient = HttpClientBuilder.create().build();
		 HttpPost request = new HttpPost(this.python_url);
		 request.setEntity(entity);

		 HttpResponse response = httpClient.execute(request);
		 System.out.println(response.getStatusLine());

		 String responseString = new BasicResponseHandler().handleResponse(response);
		 JSONObject rslt = new JSONObject(responseString);
		 System.out.println("-------------Response--------------");
		 System.out.println(rslt);
		 return responseString;
	}
	
	
}
