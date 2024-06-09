package com.springboot.cab.response;

import com.springboot.cab.domain.userrole;

import lombok.Data;

@Data
public class jwtresponse {

	private String jwt ;
	private String message ;
	private boolean isauth;
	private boolean iserror;
	private String errordetails;
	private userrole type;
	
}
