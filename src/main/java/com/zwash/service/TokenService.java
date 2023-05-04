package com.zwash.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public interface TokenService {


	public  String createJWT(String id, String issuer, String subject, long ttlMillis) throws Exception;
	
	
<<<<<<< HEAD
	public  Claims verifyJWT(String jwt) throws  ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException;
=======

	public  Claims verifyJWT(String jwt) throws  ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException;

>>>>>>> e11520111fcd663b55fb6071772482e77369f442
	  
}
