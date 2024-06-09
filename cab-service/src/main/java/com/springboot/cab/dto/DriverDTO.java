package com.springboot.cab.dto;

import com.springboot.cab.domain.userrole;
import com.springboot.cab.model.vechile;

import lombok.Data;

@Data
public class DriverDTO {

 private Integer id; 
 private String name;

private String email; 
private String mobile; 
private double rating; 
private double latitude; 
private double longitude; 
private userrole role;
private vechile vehicle;
}
