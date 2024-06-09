package com.springboot.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cab.config.Jwtprovider;
import com.springboot.cab.domain.userrole;
import com.springboot.cab.exception.driverexception;
import com.springboot.cab.exception.userexception;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.license;
import com.springboot.cab.model.user;
import com.springboot.cab.model.vechile;
import com.springboot.cab.repository.driverrepository;
import com.springboot.cab.repository.userrepository;
import com.springboot.cab.request.driversignuprequest;
import com.springboot.cab.request.loginrequest;
import com.springboot.cab.request.signuprequest;
import com.springboot.cab.response.jwtresponse;
import com.springboot.cab.service.driverservice;
import com.springboot.cab.service.userdetailsservice;



@RestController
public class authcontroller {

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private userdetailsservice userdetailsservice;
	private userrepository userrepository;
	private driverrepository driverrepository;
	@Autowired
	private driverservice driverservice;
	
	// new way without using autowired
	authcontroller(	userrepository userrepository, driverrepository driverrepository){
		this.userrepository=userrepository;
		this.driverrepository= driverrepository ;
	}

	@PostMapping("/auth/signup")
	public ResponseEntity<jwtresponse> signup(@RequestBody signuprequest req) throws userexception {
		
		
		String email= req.getEmail();
		String fullname = req.getFullname();
		String password= req.getPassword();
		String mobile= req.getMobile();
		user u = userrepository.findByEmail(email);
		if (u!=null) {
			
			throw new userexception("user alredy registered with email :"+email);
		}
	      String encpassword= passwordEncoder.encode(password);
	      user us = new user();
	      us.setEmail(email);
	      us.setFullname(fullname);
	      us.setMobile(mobile);
	      us.setPassword(encpassword);
		us.setRole(userrole.USER);
		user newu= userrepository.save(us);
		Authentication auth = new UsernamePasswordAuthenticationToken(newu.getEmail(), newu.getPassword());

		String tok = Jwtprovider.generatetoken(auth);
		jwtresponse au = new jwtresponse();
		au.setJwt(tok);
		au.setType(userrole.USER);
		au.setIserror(false);
		au.setIsauth(true);
		au.setMessage("succesfully registered with email "+email);
		
		return new ResponseEntity<jwtresponse>(au,HttpStatus.CREATED);
	}
	
	@PostMapping("/auth/signin")
	public ResponseEntity<jwtresponse>signin(@RequestBody  loginrequest req){
		
		String email= req.getEmail();
		String password=req.getPassword();
		
		Authentication auth= Authanticate(password,email);
		
		String tok = Jwtprovider.generatetoken(auth);
		jwtresponse au = new jwtresponse();
		au.setJwt(tok);
		au.setType(userrole.USER);
		au.setIserror(false);
		au.setIsauth(true);
		au.setMessage("succesfully login with email  "+email);
		
		return new ResponseEntity<jwtresponse>(au,HttpStatus.OK);
		
	}


	@PostMapping("/auth/driver/signup")
	public ResponseEntity<jwtresponse> driversignup(@RequestBody driversignuprequest req) throws driverexception {
		
		Driver newu= driverservice.signupdriver(req);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(newu.getEmail(), newu.getPassword());

		String tok = Jwtprovider.generatetoken(auth);
		jwtresponse au = new jwtresponse();
		au.setJwt(tok);
		au.setType(userrole.DRIVER);
		au.setIserror(false);
		au.setIsauth(true);
		au.setMessage("succesfully registered with email "+req.getEmail());
		
		return new ResponseEntity<jwtresponse>(au,HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	private Authentication Authanticate(String password, String email) {
		
		UserDetails userdetail= userdetailsservice.loadUserByUsername(email);
		
		if(userdetail ==null) {
			throw new BadCredentialsException("user not found with email");
		}
		if(!passwordEncoder.matches(password, userdetail.getPassword())) {
			throw new BadCredentialsException("enter correct password");
		}
		return new UsernamePasswordAuthenticationToken(userdetail,null, userdetail.getAuthorities());
	}
	
}
