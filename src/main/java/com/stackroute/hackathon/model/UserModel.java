 package com.stackroute.hackathon.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="resthackathon")
public class UserModel {
	
	@Id
	private String id;
	
	private String username;
	
	private String emailid;
	
	public UserModel() {}

	public UserModel(String id, String username, String emailid) {
		super();
		this.id = id;
		this.username = username;
		this.emailid = emailid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
}
