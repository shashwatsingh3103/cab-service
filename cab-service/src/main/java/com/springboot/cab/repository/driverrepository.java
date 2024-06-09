package com.springboot.cab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;


public interface driverrepository extends JpaRepository<Driver, Integer> {
	

	public Driver findByEmail(String email);
	
	@Query("select r from Ride r where r.ridestatus=REQUESTED and r.driver.id=:driverid")
	public List<Ride> getallocatedride(@Param("driverid") int did );

	@Query("select r from Ride r where r.ridestatus=COMPLETED and r.driver.id=:driverid")
	public List<Ride> getcompletedride(@Param("driverid") int did );
}
