package com.zwash.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
<<<<<<< HEAD
=======
import io.jsonwebtoken.ExpiredJwtException;
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public interface TokenService {


	public  String createJWT(String id, String issuer, String subject, long ttlMillis) throws Exception;
	
	
<<<<<<< HEAD
	public  Claims verifyJWT(String jwt) throws  Exception, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException;
=======
	public  Claims verifyJWT(String jwt) throws  ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException;
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
	  
}
