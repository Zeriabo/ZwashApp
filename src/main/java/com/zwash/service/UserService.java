	package com.zwash.service;
	import java.io.Serializable;
	
	import org.hibernate.service.Service;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.zwash.repository.UserRepository;
	
	import com.zwash.pojos.User;
	
	
	@Configurable
	public interface UserService extends Serializable  {
		
		 User signIn(String username, String password);
		 User register(User user) throws Exception;
		 boolean changePassword(String username, String password);
		 boolean validateSignIn(String token);
		 User getSecretQuestionAnswer(String username);
		 void sayHello();
	
		
	}
