package com.zwash.service;

import java.io.Serializable;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.User;
	
	
	public interface UserService extends Serializable  {
		
		 LoggedUser signIn(String username, String password) throws Exception;
		 User register(User user) throws Exception;
		 boolean changePassword(String username, String password) throws  Exception;
		 boolean validateSignIn(String token);
		 User getSecretQuestionAnswer(String username);
	
		
	}
