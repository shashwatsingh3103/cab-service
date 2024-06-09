package com.springboot.cab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;

public interface  userrepository extends JpaRepository<user, Integer> {

	
	public user findByEmail(String email);
	
	@Query("select r from Ride r where r.ridestatus=COMPLETED AND r.user.id=:idd"  )
	public List<Ride> getusercompletedide(@Param("idd")int id);
	
}
