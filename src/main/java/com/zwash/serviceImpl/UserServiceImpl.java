package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.User;
import com.zwash.repository.UserRepository;
import com.zwash.service.UserService;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User signIn(String username, String password) throws UserIsNotFoundException {
		
		
		User user =	userRepository.findByUsername(username);
		if(user instanceof User) {
			return user;	
		}else {
			throw new UserIsNotFoundException(username);
		}
		
	}

	public User register(User user) throws Exception {

		user.setActive(true);
		try {
		user =	userRepository.save(user);
		}catch(DataIntegrityViolationException de)
		{
			throw de;
		}
		
		catch (Exception e) {
			throw e;
		}

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

	@Override
	public void sayHello() {

		System.out.println("Hello from userservice Implementation");

	}

}
