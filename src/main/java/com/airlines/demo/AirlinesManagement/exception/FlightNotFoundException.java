package com.airlines.demo.AirlinesManagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) 
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message){
        super(message);
    }
}
