package com.springboot.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cab.dto.rideDTO;
import com.springboot.cab.dtos.mapper.dtomapper;
import com.springboot.cab.exception.driverexception;
import com.springboot.cab.exception.rideexception;
import com.springboot.cab.exception.userexception;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;
import com.springboot.cab.repository.riderepository;
import com.springboot.cab.request.riderequest;
import com.springboot.cab.request.startride;
import com.springboot.cab.service.driverservice;
import com.springboot.cab.service.rideservice;
import com.springboot.cab.service.userservice;

@RestController
public class ridecontroller {

	@Autowired
	rideservice rideservice;
	@Autowired
	driverservice driverservice;
	@Autowired
	userservice userservice;
	
	@PostMapping("/requestride")
public ResponseEntity<rideDTO> userrequestride(@RequestBody riderequest r , @RequestHeader("Authorization")String jwt) throws rideexception,userexception, driverexception{
	user u = userservice.getrequser(jwt);
	Ride ride= rideservice.requestride(r, u);
	
	rideDTO ri=dtomapper.ridetodto(ride);
	return new  ResponseEntity<rideDTO>(ri,HttpStatus.CREATED);
	
	
}

@PutMapping("/{rideid}/accept")
public ResponseEntity<String>acceptride(@PathVariable("rideid") int id ) throws rideexception,userexception, driverexception{
	
	
	
	rideservice.acceptride(id);
	
	return new ResponseEntity<String>("Ride accepted by driver", HttpStatus.OK);
}


@PutMapping("/{rideid}/decline")
public ResponseEntity<String>delclineride(@PathVariable("rideid") int id,  @RequestHeader("Authorization")String jwt ) throws rideexception,userexception, driverexception{
	
	Driver d = driverservice.getreqdriverprofile(jwt);
	
	rideservice.declineride(id, d.getId());

	
	return new ResponseEntity<String>("Ride declined by driver", HttpStatus.OK);
}

@PutMapping("/{rideid}/startride")
public ResponseEntity<String>startride(@PathVariable("rideid") int id, @RequestBody startride ride )throws rideexception,userexception, driverexception{

	rideservice.startride(id, ride.getOtp());
	return new ResponseEntity<String>("Ride started by driver", HttpStatus.OK);

}

@PutMapping("/{rideid}/complete")
public ResponseEntity<String>startride(@PathVariable("rideid") int id)throws rideexception,userexception, driverexception{

	rideservice.completeride(id);
	return new ResponseEntity<String>("Ride completed by driver", HttpStatus.OK);}


@GetMapping("/{rideid}")
public ResponseEntity<rideDTO> findridebyid(@PathVariable("rideid") int id ,@RequestHeader("Authorization")String jwt )throws rideexception, userexception{
	
	user u = userservice.getrequser(jwt);
	Ride r = rideservice.findridebyid(id);
	rideDTO rd= dtomapper.ridetodto(r);
	return new ResponseEntity<rideDTO>(rd, HttpStatus.OK);
}
}
