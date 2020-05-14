package com.example.voting;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.voting.entities.Electeur;
import com.example.voting.services.StartAngularApp;
import com.example.voting.services.StartPython;
import com.voting.blockchain.services.BlockChain;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.voting","com.voting.blockchain"} )
public class MicroVotingAppApplication {

	public static void main(String[] args){
		SpringApplication.run(MicroVotingAppApplication.class, args);
		 
			
		//System.out.println("blockchain started");
		BlockChain chain = new BlockChain();	
		// Start applictions 
		//StartAngularApp angular = new StartAngularApp();
		//StartPython python = new StartPython(); 
		
		//angular.run_Angular_();
		//python.run_Python_();
		
	} 
  
}
