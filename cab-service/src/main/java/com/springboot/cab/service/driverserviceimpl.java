package com.springboot.cab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.cab.config.Jwtprovider;
import com.springboot.cab.domain.Ridestatus;
import com.springboot.cab.domain.userrole;
import com.springboot.cab.exception.driverexception;
import com.springboot.cab.exception.userexception;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.license;
import com.springboot.cab.model.vechile;
import com.springboot.cab.repository.driverrepository;
import com.springboot.cab.repository.licenserepository;
import com.springboot.cab.repository.riderepository;
import com.springboot.cab.repository.vechilerepository;
import com.springboot.cab.request.driversignuprequest;
import com.springboot.cab.service.calculators.Calculator;

@Service
public class driverserviceimpl implements driverservice {

	@Autowired
	private driverrepository driverrepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Calculator calculator;
	@Autowired
	private vechilerepository vechilerepository;
	@Autowired
	private licenserepository licenserepository;
	@Autowired
	private riderepository riderepository;
	@Override
	public Driver signupdriver(driversignuprequest req) throws driverexception {
		

		
		String email= req.getEmail();
		String fullname = req.getFullname();
		String password= req.getPassword();
		String mobile= req.getMobile();
		double lat=req.getLatitude();
		double longi=req.getLongtitude();
		license lic= req.getLicense();
		vechile vech= req.getVechile();
		
		Driver u = driverrepository.findByEmail(email);
		if (u!=null) {
			throw new  driverexception("driver alredy exist with email"+email);
		}
	     
	      license newlic= new license();
	      newlic.setState(lic.getState());
	      newlic.setLicensenumber(lic.getLicensenumber());
	      newlic.setLicenseexpirationdate(lic.getLicenseexpirationdate());
	      
	      license savlic= licenserepository.save(newlic);
	      vechile newvech= new vechile();
	      newvech.setCapacity(vech.getCapacity());
	      newvech.setColor(vech.getColor());
	      newvech.setLicenseplate(vech.getLicenseplate());
	      newvech.setMake(vech.getMake());
	      newvech.setYear(newvech.getYear());
	      newvech.setModel(vech.getModel());
	      
	      vechile savech= vechilerepository.save(newvech);
	      
	      
	      
	      
	      
	      Driver us = new Driver();
	      String encpassword= passwordEncoder.encode(password);
	      us.setEmail(email);
	      us.setFullname(fullname);
	      us.setMobile(mobile);
	      us.setPassword(encpassword);
	      us.setVechile(vech);
	      us.setLicense(lic);
	      us.setLongtitude(longi);
	      us.setLatitude(lat);
	      us.setVechile(savech);
	      us.setLicense(savlic);
	    
		us.setRole(userrole.USER);
		Driver newu= driverrepository.save(us);
		
		savlic.setDriver(newu);
		savech.setDriver(newu);
		
		return newu;
	}
	@Override
	public List<Driver> getavailabledriver(double pikuplati, double pikuplong,  Ride ride) {
	
		List<Driver> alldriver= driverrepository.findAll();
		List<Driver> avilabledriver= new ArrayList<>();
		
		for (Driver d : alldriver) {
			if (d.getCurrentride()!=null && d.getCurrentride().getRidestatus()!=Ridestatus.COMPLETED) {
				continue;
			}
			if(ride.getDeclineddrivers().contains(d.getId())) {
				System.out.println("declined driver");
				continue ;
			}
			avilabledriver.add(d);
		}
		return avilabledriver;
		
		
	
	}
	@Override
	public Driver nearestdriver(List<Driver> availabledriver, double pikuplati, double pikuplong) {
	
		double min = Double.MAX_VALUE;
		Driver nearestdriver=null;
		for (Driver d :availabledriver) {
			double lati= d.getLatitude();
			double longt=d.getLongtitude();
			double dist = calculator.calculatedistance( pikuplati, pikuplong , lati, longt);
			if (min>dist) {
				min= dist;
				nearestdriver=d;
			}
		}
		
		
		return nearestdriver;
	}
	@Override
	public Driver getreqdriverprofile(String profile) throws driverexception {
		
		String email= Jwtprovider.getemailfromjwt(profile);
		Driver d = driverrepository.findByEmail(email);
		if (d==null) {
			throw new driverexception("driver not found"+email);
		}
		
		return d;
	}
	@Override
	public Ride getdrivercurrentride(int driverid) throws driverexception {
		
		Optional<Driver> d = driverrepository.findById(driverid);
		if (d.isEmpty()) {
			 throw new  driverexception("driver not found ");
		}
	
		return d.get().getCurrentride();
	}
	@Override
	public List<Ride> getallocatedride(int driverid) throws driverexception {
		List<Ride> alloc= driverrepository.getallocatedride(driverid);
		
		return alloc;
	}
	@Override
	public Driver finddriverbyid(int id) throws driverexception {
	
		Optional<Driver> d = driverrepository.findById(id);
		if (d.isEmpty()) {
			 throw new  driverexception("driver not found ");
		}
		return d.get();
	}
	@Override
	public List<Ride> completedride(int driverid) throws driverexception {
		 List<Ride>  comride= driverrepository.getcompletedride(driverid);
		return comride;
	}
}
