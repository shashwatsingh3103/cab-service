package com.springboot.cab.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Errordetails {

	private String message ;
	private String error ;
	private LocalDateTime time ;
	public Errordetails(String message, String error, LocalDateTime time) {
		super();
		this.message = message;
		this.error = error;
		this.time = time;
	}
	

}
