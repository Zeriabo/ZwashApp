package com.zwash.controller;

import java.util.ServiceLoader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwash.exceptions.IncorrectTokenException;
import com.zwash.exceptions.UserIsNotActiveException;
import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.SignInfo;
import com.zwash.pojos.User;
import com.zwash.security.JwtUtils;
import com.zwash.service.UserService;

import io.jsonwebtoken.Claims;


@Controller
@RequestMapping(value = "/users")
public class UserController {
	

	@Autowired
    private UserService userService;
    
    @PostMapping("/signin")
	public ResponseEntity<LoggedUser> signIn(@RequestBody  SignInfo userInfo ) throws Exception {
        

	
		LoggedUser signedUser;
		try {
			
			signedUser = userService.signIn(userInfo.getUsername(),userInfo.getPassword());

			if(signedUser instanceof LoggedUser)
			{
				if(signedUser.isActive()) {
					
					 return new ResponseEntity<LoggedUser>(
							 signedUser, HttpStatus.OK);
			
					 
				}else {
					throw new UserIsNotActiveException(userInfo.getUsername());
					
				}
			
			}else {
				 return new ResponseEntity<LoggedUser>(
						  HttpStatus.NOT_ACCEPTABLE);
			}
			
		} catch (Exception e) {
			throw e;
		}
	

	
	}
	
	
	
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody String registerDetails) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(registerDetails, User.class);

		User userCreated;
	try {
		userCreated = userService.register(user);

		if(userCreated instanceof User)
		{
			 return new ResponseEntity<>(
					 userCreated.getString(), HttpStatus.OK);
		}else {
			 return new ResponseEntity<>(
					 "not created", HttpStatus.NOT_ACCEPTABLE);
		}
	}catch(DataIntegrityViolationException dx)
	{

		return new ResponseEntity<>(
				
				 "User already exists", HttpStatus.NOT_ACCEPTABLE);
	}
	
	catch(Exception e)
	{
		throw e;
	}
			
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PostMapping("/changepassword")
	@POST
	public ResponseEntity<String> changePassword(@RequestBody String body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		User userToEdit = mapper.readValue(body, User.class);

		JwtUtils jwtUtils = new JwtUtils();
		
		try {
			Claims cl =jwtUtils.verifyJWT(userToEdit.getToken());	
			userToEdit.setUsername(cl.getId());
		}catch(Exception ex)
		{
			throw new IncorrectTokenException("The token is not valid!");
		}
		
		
		boolean changed;
		try {
			changed = userService.changePassword(userToEdit.getUsername(),userToEdit.getPassword());

			if(changed)
			{
				 return new ResponseEntity<String>("Password changed",
						HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Password not changed",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/validate")
	@GET
    public Response validateIfSigned(@QueryParam("token")  String  token) throws Exception {
  
    //  UserService userService = getUserService();
      
      boolean valid=false;
      try {
    	  
    	  valid =userService.validateSignIn(token);
      }
       catch(Exception exp)
      {
    	 
    	   return Response.status(500).entity(exp.getMessage()).build();
      }
      if(valid)
      {
    	  return Response.ok(valid).build();
    	  
      }else {
    	  
    	  return Response.ok(false).build(); 
      }
      
    	
    }
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getsecrets")
	@POST
	public Response getSecretQuestionAnswer(String user) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		User userToEdit = mapper.readValue(user, User.class);
		Response response = null;
	//	UserService userService = getUserService();

		try {
			userToEdit = userService.getSecretQuestionAnswer(userToEdit.getUsername());

			if(userToEdit instanceof User)
			{
				return Response.ok(userToEdit).build();
			}
			
		} catch (Exception e) {
		
			return Response.status(500).entity(e.getMessage()).build();
		}
	
		return response;
	
	}
	
	public static UserService getUserService()throws Exception {
	try {
		  ServiceLoader<UserService> serviceLoader =ServiceLoader.load(UserService.class);
		  UserService userService = serviceLoader.iterator().next();

			return userService;
	}catch (Exception e) {
	     	
  	  throw e;
    }

  
	}
}