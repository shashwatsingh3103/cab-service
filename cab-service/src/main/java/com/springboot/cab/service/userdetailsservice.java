package com.springboot.cab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.cab.model.Driver;
import com.springboot.cab.model.user;
import com.springboot.cab.repository.driverrepository;
import com.springboot.cab.repository.userrepository;

@Service
public class userdetailsservice implements UserDetailsService {

	
@Autowired
	userrepository userrepository;
@Autowired
	driverrepository driverrepository;
	


	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		user u = userrepository.findByEmail(username);
	
		if (u!=null) {
			
		List<GrantedAuthority> auth= new ArrayList<>();
		return new User(u.getEmail(), u.getPassword(),auth);}
		
	
		Driver d= driverrepository.findByEmail(username);
		if (d!=null) {
			
		List<GrantedAuthority> auth= new ArrayList<>();
		return new User(d.getEmail(), d.getPassword(),auth);}
		
	
	throw new UsernameNotFoundException("user not found with email : "+username);

	
	
}}
