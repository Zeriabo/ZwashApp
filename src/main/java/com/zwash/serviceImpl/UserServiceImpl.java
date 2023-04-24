package com.zwash.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ServiceLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.zwash.DatabaseConnection;
import com.zwash.exceptions.IncorrectCredentialsException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.User;
import com.zwash.repository.UserRepository;
import com.zwash.security.JwtUtils;
import com.zwash.service.TokenService;
import com.zwash.service.UserService;

import io.jsonwebtoken.Claims;


@Service
public class UserServiceImpl implements UserService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserRepository userRepository;

	TokenService tokenService = getTokenService();
	JwtUtils jwtUtils= new JwtUtils();
	public LoggedUser signIn(String username, String password) throws  Exception {
		
		
		LoggedUser user = new LoggedUser();
		String bearerToken = "";
	
		try (Connection c = DatabaseConnection.getConnection()) {
			PreparedStatement s = c.prepareStatement("SELECT * FROM zwashuser where username=? and password=?");
			s.setString(1, username);
			s.setString(2, password);
			ResultSet result = s.executeQuery();
			
		

			if (result.next()) {
				user.setId(result.getInt(1));
				user.setUsername(result.getString("username"));
				user.setActive(result.getBoolean("active"));
				user.setDateOfBirth(result.getString("date_of_birth"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
	
				
		
				//Create a JWTToken
				try {
					String jwt =tokenService.createJWT(Integer.toString(user.getId()), "Java", user.getUsername(), 1232134356);

					 user.setToken(jwt);
					
				} catch (Exception exp) {
					throw exp;
				}
				
				
				return user;
			}else {
			   throw  new IncorrectCredentialsException("Incorrect input !");
			}

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

	public boolean changePassword(String username, String password) throws Exception {
		try (Connection c = DatabaseConnection.getConnection()) {
			PreparedStatement s = c.prepareStatement("UPDATE zwashuser SET password =? WHERE username=? ");
			s.setString(2, username);
			s.setString(1, password);
			int resultCount = s.executeUpdate();
		
		     s.close();

			if (resultCount>0) {
				return true;
	
			
			}else {
				return false;
			}

		}
	}

	public boolean validateSignIn(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public User getSecretQuestionAnswer(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TokenService getTokenService() {
		   
		 ServiceLoader<TokenService> serviceLoader =ServiceLoader.load(TokenService.class);
		 for (TokenService provider : serviceLoader) {
		     return provider;
		 }
		 throw new NoClassDefFoundError("Unable to load a driver "+TokenService.class.getName());
		}

	@Override
	public User getUser(long id) throws UserIsNotFoundException {
		Optional<User> user = userRepository.findById(id);
		 if (user.isPresent()) {
		        return user.get();
		    }
		 
		   // Handle the case where the user is not found
		    throw new UserIsNotFoundException("User with id " + id + " not found");
	}

}
