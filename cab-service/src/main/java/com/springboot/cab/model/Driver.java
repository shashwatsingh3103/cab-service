package com.springboot.cab.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.cab.domain.userrole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
public class Driver {

  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String fullname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;
    private double rateg;
    private double latitude;
    private double longtitude ;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private   userrole role;

    private String password;

    @OneToOne(mappedBy = "driver" , cascade = CascadeType.ALL)
    private license license ; 

    @JsonIgnore
    @OneToMany(mappedBy="driver" , cascade = CascadeType.ALL)
    private List<Ride> rides ;

    @OneToOne(mappedBy="driver" , cascade = CascadeType.ALL , orphanRemoval = true)
    private vechile vechile;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Ride currentride;
    private int totalrevenue=0;

}
