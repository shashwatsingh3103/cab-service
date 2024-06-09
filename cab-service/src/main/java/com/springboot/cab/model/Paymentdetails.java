package com.springboot.cab.model;

import com.springboot.cab.domain.paymentstatus;

import jakarta.persistence.Embeddable;





@Embeddable
public class Paymentdetails {

	
	private paymentstatus status;
	private String paymentid ;
	private String razorpaypaymentlinkid ;
	private String razorpaypaymentlinkrefid ;
	private String razorpaypaymentstatus;
	private String razorpaypaymentid ;
	
}
