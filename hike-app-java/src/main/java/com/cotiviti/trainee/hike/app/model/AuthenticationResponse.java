package com.cotiviti.trainee.hike.app.model;

import java.util.HashSet;
import java.util.Set;

public class AuthenticationResponse {
	private String jwt;
	private Set<String> roles = new HashSet<>();

	
	
	public AuthenticationResponse(String jwt, Set<String> roles) {
		super();
		this.jwt = jwt;
		this.roles = roles;
	}



	public String getJwt() {
		return jwt;
	}



	public Set<String> getRoles() {
		return roles;
	}



	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
	
	
}
     