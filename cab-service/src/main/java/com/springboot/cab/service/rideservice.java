package com.springboot.cab.service;

import com.springboot.cab.exception.driverexception;
import com.springboot.cab.exception.rideexception;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;
import com.springboot.cab.request.riderequest;

public interface rideservice {

	
	public Ride requestride(riderequest r ,user u )throws driverexception;
	
	public Ride createriderequest(user u , Driver d , double picklat , double picklong,double destlat,
			double destlong,String pikup,String drop);
	public void acceptride(int rid) throws rideexception;
	public void declineride(int rid, int did) throws rideexception, driverexception;
	public void startride(int rid, int otp) throws rideexception;
	public void completeride(int rid) throws rideexception;
	public void cancelride(int rid) throws rideexception;
	public Ride findridebyid(int rid )throws rideexception;
	
}
