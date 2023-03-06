package com.zwash.controller;
import java.util.Iterator;
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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwash.pojos.SignInfo;
import com.zwash.pojos.User;
import com.zwash.service.UserService;


@Controller
@RequestMapping(value = "/users")
public class UserController {
	

	@Autowired
    private UserService userService;
    
    @PostMapping("/signin")
	public Response signIn(String userInfo ) throws Exception {
        
	      ObjectMapper mapper = new ObjectMapper();
		
		SignInfo user = mapper.readValue(userInfo, SignInfo.class);
		
		Response response = null;
	//	UserService userService = getUserService();
		
		User signedUser;
		try {
			
			signedUser = userService.signIn(user.getUserName(),user.getPassword());

			if(signedUser instanceof User)
			{
				if(signedUser.isActive()) {
					
					 response = Response.ok(signedUser).build();
					 
				}else {
					
					 response = Response.status(204).entity("user is not active").build();
				}
			
			}else {
		     response = Response.status(404).build();
			}
			
		} catch (Exception e) {
			throw e;
		}
	
		
	
		return response;
	
	}
	
	
	
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody String registerDetails) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(registerDetails, User.class);

		User userCreated;
	
			userCreated = userService.register(user);

			if(userCreated instanceof User)
			{
				 return new ResponseEntity<>(
						 userCreated.getString(), HttpStatus.OK);
			}else {
				 return new ResponseEntity<>(
						 "not created", HttpStatus.NOT_ACCEPTABLE);
			}
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/change_password")
	@POST
	public Response changePassword(String body) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		User userToEdit = mapper.readValue(body, User.class);

	//	UserService userService = getUserService();
		
		boolean changed;
		try {
			changed = userService.changePassword(userToEdit.getUsername(),userToEdit.getPassword());

			if(changed)
			{
				return Response.ok().build();
			}else {
				return Response.status(500).build();
			}
			
			
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
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