package com.springboot.cab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.cab.config.Jwtprovider;
import com.springboot.cab.domain.userrole;
import com.springboot.cab.exception.userexception;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;
import com.springboot.cab.repository.riderepository;
import com.springboot.cab.repository.userrepository;

@Service
public class userserviceimpl implements userservice {

	@Autowired
	userrepository userrepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	rideservice rideservice;
	
	
	@Override
	public user createuser(user u) throws userexception {
	user l= userrepository.findByEmail(u.getEmail());
	if (l!=null) {
		throw new userexception("user alredy register with email"+ u.getEmail());
	}
		
		user n= new user();
		n.setEmail(u.getEmail());
		n.setFullname(u.getFullname());
		n.setId(u.getId());
		n.setMobile(u.getMobile());
		
		n.setPassword(passwordEncoder.encode(n.getPassword()));
		n.setRole(userrole.USER);
		user nn= userrepository.save(n);
	
		
		return nn;
	}

	@Override
	public user getrequser(String jwt) throws userexception {
		
		String email= Jwtprovider.getemailfromjwt(jwt);
		user u = userrepository.findByEmail(email);
		if (u==null) {
			throw new userexception("user not found");
		}
		
		
		return u;
	}

	@Override
	public user findbyid(int id) throws userexception {
		Optional<user> u = userrepository.findById(id);
		if (u.isEmpty()) {
			throw new userexception("user not found");
		}
		return u.get();
	}

	@Override
	public user findbyemail(String email) throws userexception {
		user u = userrepository.findByEmail(email);
		if (u==null) {
			throw new userexception("user not found");
		}
		
		
		return u;
	}

	@Override
	public List<Ride> getuserride(int id) throws userexception {
		
		List<Ride> rides= userrepository.getusercompletedide(id);
		
		
		return rides ;
	}

}
