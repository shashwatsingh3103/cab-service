package com.springboot.cab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class vechile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String make;

    private String model;

    private String year;

    private String color;

    private String licenseplate;

    private int capacity;

    @JsonIgnore
    @OneToOne
    private Driver driver ;

}
