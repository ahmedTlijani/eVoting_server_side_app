package com.voting.blockchain.entities;

import javax.persistence.Column;

public class BlockData {

	private int voted_to;
	private String publicHash;
	
	
	public BlockData(int voted_to, String publicHash) {
		super();
		this.publicHash = publicHash;
		this.voted_to = voted_to;
	}
	
	public BlockData() {}
	
	public int getVoted_to() {
		return voted_to;
	}
	public void setVoted_to(int voted_to) {
		this.voted_to = voted_to;
	}
	public String getPublicHash() {
		return publicHash;
	}
	public void setPublicHash(String publicHash) {
		this.publicHash = publicHash;
	} 
	
	
	
	
	
}
