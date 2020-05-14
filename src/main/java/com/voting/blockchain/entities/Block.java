package com.voting.blockchain.entities;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import com.example.voting.entities.Parti;

public class Block {

	private String hash;
	private String previousHash;
	private BlockData  data; //our data will be a simple message.
	private Date timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	
	//Block Constructor.
	public Block(String previousHash, BlockData data) {
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date();
		this.hash = calculateHash();

	}

	public String calculateHash() {
		String calculatedhash = DigestUtils.sha256Hex(this.previousHash + this.data.getPublicHash() + Integer.toString(this.nonce));	
		return calculatedhash;
	}
	
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!this.hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = this.calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
	
	
	
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public BlockData getData() {
		return data;
	}

	public void setData(BlockData data) {
		this.data = data;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	
	
	
	
}