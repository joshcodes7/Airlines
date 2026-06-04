package com.airlines.demo.AirlinesManagement.controller;

import com.airlines.demo.AirlinesManagement.dto.FlightDto;
import com.airlines.demo.AirlinesManagement.model.Flight;
import com.airlines.demo.AirlinesManagement.model.FlightStatus;
import com.airlines.demo.AirlinesManagement.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalTime;
import java.util.List;

@RestController
@Tag(name = "Flight APIs", description = "List of flight APIs to get information on flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Cacheable("flights")
    @Operation(summary = "Get All flight details", description = "Get all the flight and its details present in the db using the get call.")
    @GetMapping("/flight/allFlights")
    public List<Flight> getById(){
        return flightService.getAllFlights();
    }

    @Operation(summary = "Add a flight", description = "Add a new flight with all the details to store it to the database.")
    @PostMapping("/flightadmin/addFlight")
    public void addFlight(@RequestBody List<Flight> flight){
        flightService.addFlight(flight);
    }

    @Operation(summary = "Modify a flight", description = "Update details/details of a flight by giving the flight id.")
    @PutMapping("/flightadmin/modifyFlight/{uid}")
    @Retryable(
            include = ResourceAccessException.class,
            exclude =  HttpClientErrorException.class,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public void modifyFlight(@PathVariable Long uid, @RequestBody Flight flight){
        flightService.modifyFlight(uid, flight);
    }

    @Operation(summary = "delete a flight", description = "delete the entire entry of the flight by specifying the flight id.")
    @DeleteMapping("/flightadmin/deleteFlight/{uid}")
    public void deleteFlight(@PathVariable Long uid){
        flightService.deleteFlight(uid);
    }

    @Operation(summary = "Change status", description = "Change the status of the flight by specifying the flight uid and the status of the flight.")
    @PutMapping("/flightadmin/changeStatus/{uid}")
    public Flight changeStatusFlight(@PathVariable Long uid, @RequestParam FlightStatus fs){
        return flightService.changeStatus(uid, fs);
    }

    @Operation(summary = "delay a flight", description = "Specify the estimated departure and arrival time which will analyze if the flight is delayed or not.")
    @PutMapping("/flightadmin/delayFlight")
    public Flight delayFlight(@RequestParam Long uid, @RequestParam LocalTime edt, @RequestParam LocalTime eat){
        return flightService.delayFlight(uid, edt, eat);
    }

    @GetMapping("/flightadmin/getCrewForFlight/{flightNUmber}")
    public ResponseEntity<FlightDto> importCrewDetails(@PathVariable Integer flightNumber){
        FlightDto response = flightService.getCrewForFlight(flightNumber);
        return ResponseEntity.ok(response);
    }
}
