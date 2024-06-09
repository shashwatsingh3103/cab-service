package com.springboot.cab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.cab.exception.driverexception;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;
import com.springboot.cab.request.driversignuprequest;


public interface driverservice {

	public  Driver signupdriver(driversignuprequest d) throws driverexception;
	public List<Driver> getavailabledriver(double pikuplati,double pikuplong,  Ride ride);
	
	public Driver nearestdriver(List<Driver> availabledriver,double pikuplati,double pikuplong) ;
	
	public Driver getreqdriverprofile(String profile) throws driverexception;
	
	public Ride getdrivercurrentride(int driverid) throws driverexception;
	
	public List<Ride> getallocatedride(int driverid) throws driverexception;
	
	public Driver finddriverbyid(int id)throws driverexception;
	
	public List<Ride> completedride(int driverid) throws driverexception;
}
