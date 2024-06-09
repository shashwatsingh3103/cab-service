package com.springboot.cab.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.cab.domain.Ridestatus;
import com.springboot.cab.exception.driverexception;
import com.springboot.cab.exception.rideexception;
import com.springboot.cab.model.Driver;
import com.springboot.cab.model.Ride;
import com.springboot.cab.model.user;
import com.springboot.cab.repository.driverrepository;
import com.springboot.cab.repository.licenserepository;
import com.springboot.cab.repository.riderepository;
import com.springboot.cab.repository.vechilerepository;
import com.springboot.cab.request.riderequest;
import com.springboot.cab.service.calculators.Calculator;


@Service
public class rideserviceimpl  implements rideservice{

	@Autowired
	private Calculator calculator;
	@Autowired
	private vechilerepository vechilerepository;
	@Autowired
	private licenserepository licenserepository;
	@Autowired
	private riderepository riderepository;
	@Autowired
	private driverrepository driverrepository;
	@Autowired
	private driverservice driverservice;
	@Override
	public Ride requestride(riderequest r, user u) throws driverexception {
	
	double picklat=r.getPikuplongitude();
	double picklong=	r.getPikuplatitude();
	double droplat=r.getDropuplatitude();
	double droplong=	r.getDropuplongitude();
	String pickarea=	r.getDropuparea();
	String droparea=	r.getPikuparea();
	
	Ride exride= new Ride();
	
		List<Driver> avildriver= driverservice.getavailabledriver(picklat, picklong, exride);
		
		Driver nearestdriver= driverservice.nearestdriver(avildriver, picklat, picklong);
		if (nearestdriver==null) {
			throw new driverexception("driver not availabe ");
		}
		
			Ride ride = createriderequest(u, nearestdriver, picklat, picklong, droplat, droplong, pickarea, droparea);
			
		
		return ride;
	}
	@Override
	public Ride createriderequest(user u, Driver d, double picklat, double picklong, double destlat, double destlong,
			String pikup, String drop) {
	
		Ride r= new Ride();
		r.setUser(u);
		r.setDriver(d);
		r.setPikuplatitude(picklat);
		r.setPikuplongitude(picklong);
		r.setDropuplatitude(destlat);
		r.setDropuplongitude(destlong);
		r.setPikuparea(pikup);
		r.setDropuparea(drop);
		r.setRidestatus(Ridestatus.REQUESTED);
		r.setDistance(calculator.calculatedistance(picklat, picklong, destlat, destlong));
		return riderepository.save(r);
		
		
	}
	@Override
	public void acceptride(int rid) throws rideexception {
		
		Optional<Ride> r = riderepository.findById(rid);
		if (r.isEmpty()){
			throw new rideexception("ride not found");
		}
		
		r.get().setRidestatus(Ridestatus.ACCEPTED);
		Driver d= r.get().getDriver();
		d.setCurrentride(r.get());
		Random random = new Random();
		int otp= random.nextInt(9000)+1000;
		r.get().setOtp(otp);
		driverrepository.save(d);
		riderepository.save(r.get());
		
		
		
	}
	@Override
	public void declineride(int rid, int did) throws rideexception, driverexception {
		
		
		
		Optional<Ride> rr = riderepository.findById(rid);
		
		if (rr.isEmpty()){
			throw new rideexception("ride not found");
		}
		Ride r= rr.get();
		Driver d= driverservice.finddriverbyid(did);
		
		r.getDeclineddrivers().add(d);
		
	List<Driver> avildriver= driverservice.getavailabledriver(r.getPikuplatitude(), r.getPikuplongitude(), r);
		
		Driver nearestdriver= driverservice.nearestdriver(avildriver,r.getPikuplatitude(), r.getPikuplongitude());
		
		r.setDriver(nearestdriver);
		riderepository.save(r);
		
		
	}
	@Override
	public void startride(int rid, int otp) throws rideexception {
	
		Optional<Ride> r = riderepository.findById(rid);
		
		if (r.isEmpty()) {
			throw new rideexception("ride not found");
		}
		Ride rr= r.get();
		if(otp!=rr.getOtp()) {
			throw new rideexception("invalid otp");
		}
		rr.setRidestatus(Ridestatus.STARTED);
		rr.setStarttime(LocalDateTime.now());
		riderepository.save(rr);
		
		
		
	}
	@Override
	public void completeride(int rid) throws rideexception {
		
		
		
		Optional<Ride> r = riderepository.findById(rid);
		
		if (r.isEmpty()) {
			throw new rideexception("ride not found");
		}
		Ride rr= r.get();
		rr.setRidestatus(Ridestatus.COMPLETED);
		rr.setEndtime(LocalDateTime.now());
		double distance =	calculator.calculatedistance(rr.getPikuplatitude(), rr.getPikuplongitude(), rr.getDropuplatitude(), rr.getDropuplongitude());
		rr.setDistance(distance);
		
		
		long duration = calculator.calculateduration(rr.getStarttime(),rr.getEndtime());
		
		double fare= calculator.calculatefare(distance);
		
		rr.setDistance(Math.round(distance*100.0)/100.0);
		rr.setFair((int )(Math.round(fare)));
		
		Driver d= rr.getDriver();
				d.getRides().add(rr);
				d.setCurrentride(null);
				int rev=(int) (d.getTotalrevenue()+Math.round(0.8*fare));
				d.setTotalrevenue(rev);
				driverrepository.save(d);
				riderepository.save(rr);
				
				
		
		
		
		
		
	}
	@Override
	public void cancelride(int rid) throws rideexception {

		Optional<Ride> r = riderepository.findById(rid);
		
		if (r.isEmpty()) {
			throw new rideexception("ride not found");
		}
		Ride rr= r.get();
		rr.setRidestatus(Ridestatus.CANCLED);
		riderepository.save(rr);
		
	}
	@Override
	public Ride findridebyid(int rid) throws rideexception {
Optional<Ride> r = riderepository.findById(rid);
		
		if (r.isEmpty()) {
			throw new rideexception("ride not found");
		}
		return  r.get();
	}
	
}
