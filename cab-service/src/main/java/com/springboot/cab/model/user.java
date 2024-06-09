package com.springboot.cab.model;

import com.springboot.cab.domain.userrole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String fullname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    private String password;


    @Enumerated(EnumType.STRING)
    private userrole role;

}
