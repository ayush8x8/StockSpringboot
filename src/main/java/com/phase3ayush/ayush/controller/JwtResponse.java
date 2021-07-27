package com.phase3ayush.ayush.controller;

import java.io.Serializable;

import com.phase3ayush.ayush.entities.User1;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private User1 user;

	public JwtResponse(String jwttoken, User1 user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public User1 getUser() {
		return user;
	}
	
	public void setUser(User1 user) {
		this.user = user;
	}
}