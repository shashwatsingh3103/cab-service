package com.springboot.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.cab.model.Ride;

public interface riderepository extends JpaRepository<Ride, Integer> {

	
}
