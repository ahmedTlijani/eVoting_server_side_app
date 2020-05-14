package com.example.voting.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartPython {

	private String python_absolute_path="D:\\Ahmed\\esip\\BLOCK-CHAIN\\python-api\\API_PYTHON__";
	private String script_name="python-api-verification";
	private String python_version ="python";
	/*
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		StartPython s = new StartPython();
		s.run_Python_();
		
	}
	*/

	
	public void run_Python_() throws IOException
	{
		System.out.println("--------------Python------------");
		String script_ = this.script_name + ".py" ;
		String[] commands = {
				"cd "+this.python_absolute_path,
				"d:",
				"set FLASK_APP="+ script_,
				"set FLASK_ENV=development",
				"flask run"};
		
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
	
	
	
	public void run_Python() throws IOException
	{
		String script_path = this.python_absolute_path + "/" +this.script_name + ".py" ;
		String command = this.python_version + " " + script_path;
		
		try
        { 
		Runtime rt = Runtime.getRuntime();
		rt.exec(" cmd.exe /c start " + command + " + \"  ");
        } 
        catch (Exception e) 
        { 
            System.out.println("HEY Buddy ! U r Doing Something Wrong "); 
            e.printStackTrace(); 
        } 
	}
	
	
	
}
