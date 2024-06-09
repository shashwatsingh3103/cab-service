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
public class license {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String licensenumber ;
    private String state ;
    private String licenseexpirationdate ;

    @JsonIgnore
    @OneToOne
    private Driver driver ;

}
