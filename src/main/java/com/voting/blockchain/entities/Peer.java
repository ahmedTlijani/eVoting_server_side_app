package com.voting.blockchain.entities;

import java.util.Date;

public class Peer {

	
	String peer_id;
	Date connection_time;
	
	
	public Peer(String peer_id) {
		super();
		this.peer_id = peer_id;
		this.setConnection_time();
	}
	
	public Peer() {}
	
	public String getPeer_id() {
		return peer_id;
	}
	public void setPeer_id(String peer_id) {
		this.peer_id = peer_id;
	}
	public Date getConnection_time() {
		return connection_time;
	}
	
	public void setConnection_time() {
		this.connection_time = new Date();
	}
	
	 
	
	
}
