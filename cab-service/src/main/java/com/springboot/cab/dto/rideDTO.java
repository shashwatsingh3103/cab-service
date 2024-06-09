package com.springboot.cab.dto;

import java.time.LocalDateTime;

import com.springboot.cab.domain.Ridestatus;
import com.springboot.cab.model.Paymentdetails;

import lombok.Data;

@Data
public class rideDTO {


private Integer id;
private UserDTO user; 
private DriverDTO driver;
private double pickupLatitude;
private double pickupLongitude; 
private double destinationLatitude;
private double destinationLongitude; 
private String pickupArea;
private String destinationArea;
private double distance;

private Ridestatus status;
private LocalDateTime startTime;
private LocalDateTime endTime;
private double fare;
private Paymentdetails paymentDetails; 
private int otp;
}
