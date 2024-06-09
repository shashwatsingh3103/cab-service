package com.springboot.cab.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.cab.domain.Ridestatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @ManyToOne
    private user user ;

    @ManyToOne
    private Driver driver;

    @JsonIgnore
    @OneToMany
    private List<Driver> declineddrivers = new ArrayList<>();

    private double pikuplatitude;
    private double pikuplongitude;
    private double dropuplatitude;
    private double dropuplongitude;
    private String pikuparea ;
    private String dropuparea;
    private double distance;

    @Enumerated(EnumType.STRING)
    private Ridestatus ridestatus;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private double fair;
    private int otp;

    @Embedded
    private Paymentdetails paymentdetails = new Paymentdetails();

}
