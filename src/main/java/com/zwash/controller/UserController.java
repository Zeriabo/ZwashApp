package com.zwash.controller;

import java.util.ServiceLoader;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwash.exceptions.IncorrectTokenException;
import com.zwash.exceptions.UserIsNotActiveException;
import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.SignInfo;
import com.zwash.pojos.User;
import com.zwash.security.JwtUtils;
import com.zwash.service.UserService;
import org.springframework.http.MediaType;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/users")
public class UserController {


	@Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);


	  @GetMapping("/")
	    public ModelAndView home() {
		  return new ModelAndView("users");
	    }

	@PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Signs in an existing user.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "username", value = "The username of the user to sign in.", required = true, dataType = "string", paramType = "body"),
	    @ApiImplicitParam(name = "password", value = "The password of the user to sign in.", required = true, dataType = "string", paramType = "body")
	})
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User signed in successfully."),
	    @ApiResponse(code = 401, message = "Invalid username or password."),
	    @ApiResponse(code = 500, message = "Internal server error.")
	})
	public ResponseEntity<LoggedUser> signIn(@RequestBody SignInfo userInfo ) throws Exception {

		
		LoggedUser signedUser;
		try {

			signedUser = userService.signIn(userInfo.getUsername(),userInfo.getPassword());

			if(signedUser instanceof LoggedUser)
			{
				if(signedUser.isActive()) {

					 return new ResponseEntity<>(
							 signedUser, HttpStatus.OK);


				}else {
					throw new UserIsNotActiveException(userInfo.getUsername());

				}

			}else {
				 return new ResponseEntity<>(
						  HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			throw e;
		}



	}




	/**
	 * Registers a new user.
	 *
	 * @param registerDetails the user registration details in JSON format
	 * @return a response entity indicating whether the registration was successful or not
	 * @throws Exception if there is an error during the registration process
	 */
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Registers a new user.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User created successfully."),
		@ApiResponse(code = 406, message = "User already exists."),
		@ApiResponse(code = 500, message = "Internal server error.")
	})
	public ResponseEntity<String> register(@RequestBody User user) throws Exception {
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


	@PostMapping(value = "/changepassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Change password for a user.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Password changed successfully."),
	    @ApiResponse(code = 401, message = "Unauthorized access."),
	    @ApiResponse(code = 500, message = "Internal server error.")
	})
	public ResponseEntity<String> changePassword(@RequestBody User user) throws Exception {

	
		JwtUtils jwtUtils = new JwtUtils();

		try {
			Claims cl =jwtUtils.verifyJWT(user.getToken());
			user.setUsername(cl.getId());
		}catch(Exception ex)
		{
			throw new IncorrectTokenException("The token is not valid!");
		}


		boolean changed;
		try {
			changed = userService.changePassword(user.getUsername(),user.getPassword());

			if(changed)
			{
				 return new ResponseEntity<>("Password changed",
						HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Password not changed",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}


		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}
	/**
	 * Validates if the user is signed in.
	 *
	 * @param token JWT token of the user
	 * @return Response indicating whether the user is signed in or not
	 * @throws Exception if there is an error while validating the user
	 */
	@GetMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Validates if the user is signed in.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "User is signed in."),
	    @ApiResponse(code = 200, message = "User is not signed in."),
	    @ApiResponse(code = 500, message = "Internal server error.")
	})
	public Response validateIfSigned(
	        @ApiParam(value = "JWT token of the user", required = true) @QueryParam("token") String token)
	        throws Exception {

	    boolean valid = false;
	    try {
	        valid = userService.validateSignIn(token);
	    } catch (Exception exp) {
	        return Response.status(500).entity(exp.getMessage()).build();
	    }
	    if (valid) {
	        return Response.ok(valid).build();
	    } else {
	        return Response.ok(false).build();
	    }
	}


	@PostMapping(value = "/getsecrets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get secret question and answer for a user.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Secret question and answer retrieved successfully."),
	    @ApiResponse(code = 500, message = "Internal server error.")
	})
	public Response getSecretQuestionAnswer(@RequestBody User user) throws Exception {
	    try {
	        user.setSecretAnswer(userService.getSecretQuestionAnswer(user.getUsername()));

	        if(user instanceof User) {
	            return Response.ok(true).build();
	        }
	    } catch (Exception e) {
	        return Response.status(500).entity(e.getMessage()).build();
	    }

	    return Response.status(500).entity("User not found").build();
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