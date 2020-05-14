package com.example.voting.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartAngularApp {

	private String angular_absolute_path="D:\\Ahmed\\esip\\BLOCK-CHAIN\\angular-app\\voting-angular-app";
	
	/*
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 
	}
	*/

	
	public void run_Angular_() throws IOException
	{
		System.out.println("--------------Angular------------");
		String[] commands = {
				"cd "+this.angular_absolute_path,
				"d:",
				"ng serve"};
		
		String executed_command ="";
		
		for(int i=0; i< commands.length -1 ;i++)
		{
			executed_command += commands[i] +" && "; 
		}
		executed_command += commands[commands.length-1];
		System.out.println("Cammande: "+ executed_command);
		
		try
        { 
		Runtime rt = Runtime.getRuntime();
		rt.exec(" cmd.exe /c start cmd.exe /K \"" + executed_command + "\"  ");
        } 
        catch (Exception e) 
        { 
            System.out.println("HEY Buddy ! U r Doing Something Wrong "); 
            e.printStackTrace(); 
        } 
		
	}
	
	
	
	
}
