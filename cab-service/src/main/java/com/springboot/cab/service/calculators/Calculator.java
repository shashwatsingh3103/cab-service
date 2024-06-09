package com.springboot.cab.service.calculators;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

	private static final int EARTH_RADIUS=6371;
	
	public double calculatedistance(double slat, double slong,double dlat, double dlong) {
		double dla= Math.toRadians(dlat-slat);
		double dlo= Math.toRadians(dlong-slong);
		double a = Math.sin(dla/2)*Math.sin(dla/2)+
				Math.cos(Math.toRadians(slat) *Math.toRadians(dlat))*
				Math.sin(dlo/2)*Math.sin(dlo/2);
		double c=2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist=EARTH_RADIUS*c;
		return dist;
		
	}
	
	public long calculateduration(LocalDateTime starttime, LocalDateTime endtime) {
		Duration duration =Duration.between(starttime, endtime);
		return duration.getSeconds();
	}
	
	public  double calculatefare(double dist) {
		double basefare=11;
		double total= basefare*dist;
		return total;
	}
}

