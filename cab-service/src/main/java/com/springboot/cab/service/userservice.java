package com.springboot.cab.service;

import java.util.List;

import com.springboot.cab.exception.userexception;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;

public interface userservice {

	public user createuser(user u)throws userexception;
	public user getrequser(String jwt)throws userexception;
	public user findbyid(int id)throws userexception;
	public user findbyemail(String email)throws userexception;
	public List<Ride> getuserride(int id)throws userexception;

}
