package com.example.voting.services;

import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

// https://kodejava.org/how-do-i-send-post-request-with-a-json-body-using-the-httpclient/
public class TestPostRequest {

	private final String USER_AGENT = "Mozilla/5.0";
	private String python_url= "http://127.0.0.1:5000/api/test-post-request/";
	
	
	/*public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 
			//TestPostRequest http = new TestPostRequest();
			//http.sendPost();
			
		
		
		
		 
	} */
	
	
		// HTTP POST request
		public void sendPost() throws Exception {

			String cin = "09987673";
			String known_image ="known_image";
			String unknown_image ="unknown_image";
			
			JSONObject obj = new JSONObject();
			obj.put("cin", cin);
			obj.put("known_image", known_image);
			obj.put("unknown_image", unknown_image);
			
			String payload = obj.toString();
			
			System.out.println("--------------------------");
			System.out.println(payload);
			
	        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON); //APPLICATION_FORM_URLENCODED

	        HttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost request = new HttpPost(this.python_url);
	        request.setEntity(entity);

	        HttpResponse response = httpClient.execute(request);
	        System.out.println(response.getStatusLine());

	        String responseString = new BasicResponseHandler().handleResponse(response);
	        JSONObject rslt = new JSONObject(responseString);
	        System.out.println(rslt.get("message"));

		}
}