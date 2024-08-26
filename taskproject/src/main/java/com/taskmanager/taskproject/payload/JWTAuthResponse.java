package com.taskmanager.taskproject.payload;

import lombok.Getter;


@Getter


public class JWTAuthResponse {
	private String token;
	public String getToken() {
		return token;
	}
	public String getTokenType() {
		return tokenType;
	}
	private String tokenType ="Bearer";
	public JWTAuthResponse(String token) {
		this.token=token;
	}
	
	

}
