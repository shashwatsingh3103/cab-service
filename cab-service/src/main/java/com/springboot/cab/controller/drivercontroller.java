package com.springboot.cab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cab.exception.driverexception;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;
import com.springboot.cab.repository.driverrepository;
import com.springboot.cab.service.driverservice;

@RestController
@RequestMapping("/driver")
public class drivercontroller {

	@Autowired
	driverservice driverservice;
	
	@GetMapping("/profile")
	public ResponseEntity<	Driver> getdriverprofile(@RequestHeader("Authorization") String jwt) throws driverexception{
		
		Driver d = driverservice.getreqdriverprofile(jwt);
		return new ResponseEntity<Driver>(d,HttpStatus.OK);
	}
	
	@GetMapping("/{did}/currentride")
	public ResponseEntity<Ride>getdrivercurrentride(@PathVariable("did") int did) throws driverexception{
		Ride ride= driverservice.getdrivercurrentride(did);
		return new ResponseEntity<Ride>(ride,HttpStatus.OK);
	}
	@GetMapping("/{did}/allocated")
	public ResponseEntity<List<Ride>>getallocatedride(@PathVariable("did") int did) throws driverexception{
		List<Ride> ride= driverservice.getallocatedride(did);
		return new ResponseEntity<List<Ride>>(ride,HttpStatus.OK);
	}
	
	@GetMapping("/completed")
	public ResponseEntity<List<Ride>>getcompletedride(@RequestHeader("Authorization")String jwt) throws driverexception{
		Driver d = driverservice.getreqdriverprofile(jwt);
		List<Ride> ride=driverservice.completedride(d.getId());
		
		return new ResponseEntity<List<Ride>>(ride,HttpStatus.OK);
	}
	
}
