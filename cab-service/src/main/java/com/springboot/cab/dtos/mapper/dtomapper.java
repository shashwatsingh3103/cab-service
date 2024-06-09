package com.springboot.cab.dtos.mapper;

import com.springboot.cab.dto.DriverDTO;
import com.springboot.cab.dto.UserDTO;
import com.springboot.cab.dto.rideDTO;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;

public class dtomapper {

	public static DriverDTO drivertodto(Driver driver) {

DriverDTO driverDto=new DriverDTO();
driverDto.setEmail (driver.getEmail()); 
driverDto.setId(driver.getId()); 
driverDto.setLatitude(driver.getLatitude());
driverDto.setLongitude (driver.getLongtitude());
driverDto.setMobile(driver.getMobile());
driverDto.setName(driver.getFullname());
driverDto.setRating(driver.getRateg());
driverDto.setRole(driver.getRole());
driverDto.setVehicle(driver.getVechile());
return driverDto;}


public static UserDTO usertodto(user u ) {
	
	UserDTO uu= new UserDTO();
	uu.setId(u.getId());
	uu.setEmail(u.getEmail());
	uu.setName(u.getFullname());
	uu.setMobile(u.getMobile());
	return uu;
}

public static rideDTO ridetodto(Ride ride ) {

DriverDTO driverDTO= drivertodto(ride.getDriver()); 
UserDTO userDto =usertodto(ride.getUser());
rideDTO rideDto=new rideDTO();
rideDto.setDestinationLatitude(ride.getDropuplatitude());
rideDto.setDestinationLongitude (ride.getDropuplongitude());
rideDto.setDistance(ride.getDistance());
rideDto.setDriver(driverDTO); 

rideDto.setEndTime(ride.getEndtime());
rideDto.setFare(ride.getFair()); 
rideDto.setId(ride.getId());
rideDto.setPickupLatitude(ride.getPikuplatitude()); rideDto.setPickupLongitude (ride.getPikuplongitude());
rideDto.setStartTime(ride.getStarttime());
rideDto.setStatus(ride.getRidestatus());
rideDto.setUser(userDto);
return rideDto;
}
}