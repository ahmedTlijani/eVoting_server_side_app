package com.voting.blockchain.services;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import com.example.voting.entities.Electeur;
import com.example.voting.entities.Parti;
import com.voting.blockchain.entities.Block;
import com.voting.blockchain.entities.BlockData;
import com.voting.blockchain.entities.Peer;

@Service
public class BlockChain {

	private ArrayList<Block> blockchain = new ArrayList<Block>(); 
	private ArrayList<Peer> connected_peers = new ArrayList<Peer>();
	public static int difficulty = 3;

 
	/*

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		BlockChain chain = new BlockChain();
		chain.createChainData(); // _init_ blockchain  
		
		System.out.println("Trying to Mine block 1... ");
		chain.getBlockChain().get(0).mineBlock(difficulty);
		
		
		BlockData p1 = new BlockData(1,"az3e5aze4a8ze4");
		Block block1 = new Block(chain.getBlockChain().get(chain.getBlockChain().size()-1).getHash(),p1); // hash / data
		chain.addBlock(block1);

		System.out.println("Trying to Mine block 2... ");
		chain.getBlockChain().get(1).mineBlock(difficulty);
		
		BlockData p2 = new BlockData(2,"az3e5aze4a8ze4");
		Block block2 = new Block(DigestUtils.sha256Hex("p3"),p2); // hash / data
		chain.addBlock(block2);

		System.out.println("Trying to Mine block 3... ");
		chain.getBlockChain().get(2).mineBlock(difficulty);
		
		
		JSONArray chain__ = chain.getChainData();
		
		System.out.println("--------------- Chaindata ---------------");
		System.out.println(chain__);
		
		System.out.println(chain.isChainValid());

	}
	*/
	public  void createChainData() throws ParseException
	{
		BlockData p = new BlockData(0,null);
		Block genesis = new Block("0",p); // hash / data		
		this.blockchain.add(genesis);// base block
	}
	
	public JSONArray getChainData()
	{  
		JSONArray jsArray = new JSONArray(blockchain);
		return jsArray;
	 
	}
	  
	// 
	public ArrayList<Block> getBlockChain()
	{
		return this.blockchain;
	}
	
	public boolean setBlockChain(ArrayList<Block> blockchain)
	{
		this.blockchain = blockchain;
		return true;
	}
	
	
	
	public Boolean isChainValid() {

		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		//loop through blockchain to check hashes:
		for(int i=1; i < this.blockchain.size(); i++) {
			currentBlock = this.blockchain.get(i);
			previousBlock = this.blockchain.get(i-1);
			
			//compare previous hash and registered previous hash
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			
			//compare previous hash and registered previous hash
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
			
		}
		return true;
	}

	
	public void addBlock(Block block)
	{
		block.setPreviousHash(blockchain.get(blockchain.size()-1).getHash());
		this.blockchain.add(block);
	}
	
	public Block createBlock(Electeur electeur, Parti parti) // creating block with electeur and to who he voted 
	{
		BlockData p = new BlockData(parti.getParti_id(),electeur.getHash_code()); // votedTo ,voter public hash
		Block block = new Block(this.blockchain.get(this.blockchain.size()-1).getHash(),p);
		return block;
	}
	
	
	/*
	*JSONObject a = (JSONObject) chain.getJSONObject(0).get("data");
	*System.out.println(a.get("parti_id"));
	*/
	
	// peers methods 
	
	public boolean addPeer(String peer_id)
	{
				
		boolean verif = false;
		
		for(int i=0;i<this.connected_peers.size();i++)
		{
			if(this.connected_peers.get(i).getPeer_id().equals(peer_id))
			{
				verif = true;
				break;
			}
		}
			
		if(!verif)
		{
			Peer p = new Peer(peer_id);
			this.connected_peers.add(p);
			System.out.println("Adding peer...");
			return true; 
		}else
		{
			System.out.println("Peer exist");
			return false;
		}
		
	}
	
	public boolean removePeer(String peer_id)
	{
		boolean verif = false;
		
		for(int i=0;i<this.connected_peers.size();i++)
		{
			if(this.connected_peers.get(i).getPeer_id().equals(peer_id))
			{
				this.connected_peers.remove(i);
				verif = true;
				break;
			}
		}
		return verif;
	}
	
	public ArrayList<Peer> getConnected_peers()
	{
		//System.out.println("connected peers");
		return this.connected_peers;
	}
	
	
}
