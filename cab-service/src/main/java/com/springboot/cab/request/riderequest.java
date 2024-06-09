package com.springboot.cab.request;

import lombok.Data;

@Data
public class riderequest {

    private double pikuplatitude;
    private double pikuplongitude;
    private double dropuplatitude;
    private double dropuplongitude;
    private String pikuparea ;
    private String dropuparea;
 
}
