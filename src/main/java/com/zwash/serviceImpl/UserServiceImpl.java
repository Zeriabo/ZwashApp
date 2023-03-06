package com.zwash.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import com.zwash.pojos.User;
import com.zwash.repository.UserRepository;
import com.zwash.service.UserService;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User signIn(String username, String password) {
		return null;
	}

	public User register(User user) throws Exception {

		user.setActive(true);
		try {
		user =	userRepository.save(user);
		} catch (Exception e) {
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
