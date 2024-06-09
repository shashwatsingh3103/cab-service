package com.springboot.cab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cab.exception.userexception;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;
import com.springboot.cab.service.driverservice;
import com.springboot.cab.service.rideservice;
import com.springboot.cab.service.userservice;

@RestController
public class usercontroller {

	@Autowired
	rideservice rideservice;
	@Autowired
	driverservice driverservice;
	@Autowired
	userservice userservice;
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<user>finduserbyid(@PathVariable("id")int id )throws userexception{
		user u = userservice.findbyid(id);
		return new ResponseEntity<user>(u,HttpStatus.OK);
	}
	
	
	@GetMapping("/user/ride/completes")
	public ResponseEntity<List<Ride>>findusercompletedrides(@RequestHeader("Authorization") String jwt)throws userexception{
		user u = userservice.getrequser(jwt);
		List<Ride> ur=userservice.getuserride(u.getId());
		return new ResponseEntity<List<Ride>>(ur,HttpStatus.OK);
	}
	@GetMapping("/user/profile")
	public ResponseEntity<user>userprofile(@RequestHeader("Authorization") String jwt)throws userexception{
		user u = userservice.getrequser(jwt);
		return new ResponseEntity<user>(u,HttpStatus.OK);}
	
}



