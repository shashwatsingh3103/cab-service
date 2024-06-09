package com.springboot.cab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homecontroller {

	@GetMapping("/auth/home")
	public ResponseEntity<String> home(){
		return new  ResponseEntity<String>("welcome to ola rest api", HttpStatus.ACCEPTED);
	}
}
