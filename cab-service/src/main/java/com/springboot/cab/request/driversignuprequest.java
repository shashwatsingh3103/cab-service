package com.springboot.cab.request;

import com.springboot.cab.model.license;
import com.springboot.cab.model.vechile;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class driversignuprequest {

	   private String fullname;

	
	    private String email;

	private String password;
	    private String mobile;

	    private double latitude;
	    private double longtitude ;
	    
	
	    private license license;
	   
	    private vechile vechile;
	    
}
