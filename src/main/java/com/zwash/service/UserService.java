package com.zwash.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwash.pojos.User;



public class UserService {
	public UserService()
	{
		
	}
     
	public User signIn(String username, String password)
	{
		return null;
	}

	public User register(User user)  throws Exception {
		

		ObjectMapper mapper = new ObjectMapper();
		
		Response response = null;
		
   return user;
	
	}
	
	public boolean changePassword(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateSignIn(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public User getSecretQuestionAnswer(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
