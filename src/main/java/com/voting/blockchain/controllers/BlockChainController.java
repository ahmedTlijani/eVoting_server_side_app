package com.voting.blockchain.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.voting.entities.Adresse;
import com.example.voting.entities.Electeur;
import com.voting.blockchain.entities.Block;
import com.voting.blockchain.entities.Peer;
import com.voting.blockchain.services.BlockChain;


@RestController
@RequestMapping("api/blockchain")
//@CrossOrigin(origins = "http://localhost:4200") // cross origin angular app
@CrossOrigin()


public class BlockChainController {
		
			@Autowired
			BlockChain block;
			
			// create Peer
			@RequestMapping("/{peer_id}/create") 
			public boolean addPeer(@PathVariable("peer_id") String peer_id)
			{
				System.out.println(peer_id);
				 return this.block.addPeer(peer_id);
		 	}
			
			// delete Voter
			@DeleteMapping("/delete/{id}")
			public boolean deleteVoter(@PathVariable("id") String peer_id) {
				return this.block.removePeer(peer_id);
			}
			
			// get all peers
			@RequestMapping("/get-all-peers") 
			public ArrayList<Peer> getConnectedpeers() {
				//System.out.println("getting...");
				return this.block.getConnected_peers();
			}
			
			// get the blockahin
			@RequestMapping("/get-blockchain") 
			public ArrayList<Block> getBlockchain() {
				System.out.println("getting blockchain...");
				return this.block.getBlockChain();
			}
			
			// set the blockchain 
			@PostMapping("/set-blockchain") 
			public boolean setBlockchain(@RequestBody ArrayList<Block> blockchain) throws Exception
			{
				System.out.println("blockchain from peer ");
				//System.out.println(blockchain.get(0).getHash());
				return this.block.setBlockChain(blockchain);
		 	}
			
			
			
}
