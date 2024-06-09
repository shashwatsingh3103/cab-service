package com.springboot.cab.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class globalexception {

	
	
	
	@ExceptionHandler(userexception.class)
	public ResponseEntity<Errordetails> userExceptionhandler(userexception ue, WebRequest req){
		
		Errordetails errordetails = new Errordetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
		
		return new ResponseEntity<Errordetails>(errordetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(driverexception.class)
	public ResponseEntity<Errordetails> driverExceptionhandler(driverexception ue, WebRequest req){
		
		Errordetails errordetails = new Errordetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
		
		return new ResponseEntity<Errordetails>(errordetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(rideexception.class)
	public ResponseEntity<Errordetails> rideExceptionhandler(rideexception ue, WebRequest req){
		
		Errordetails errordetails = new Errordetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
		
		return new ResponseEntity<Errordetails>(errordetails,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Errordetails> otherExceptionhandler(Exception ue, WebRequest req){
		
		Errordetails errordetails = new Errordetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
		
		return new ResponseEntity<Errordetails>(errordetails,HttpStatus.BAD_REQUEST);
	}
	
}
