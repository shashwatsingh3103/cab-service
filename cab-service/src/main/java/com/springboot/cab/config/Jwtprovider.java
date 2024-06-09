package com.springboot.cab.config;

import java.beans.JavaBean;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class Jwtprovider {

	
	private static  SecretKey key =  Keys.hmacShaKeyFor(jwtcontant.key.getBytes());
	
	
	public static  String generatetoken(Authentication auth ) {
		
		String jwt = Jwts.builder()
				.setIssuer("shas")
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+89007678))
		.claim("email", auth.getName())
		.signWith(key)
		.compact();
		return jwt;
	}
	
	public static String getemailfromjwt(String jwt) {
		jwt= jwt.substring(7);
		Claims claim = Jwts.parser()
				.setSigningKey(key).build()
				.parseClaimsJws(jwt)
				.getBody();
		String email= String.valueOf(claim.get("email"));
		return email;
				
	}
	
}
