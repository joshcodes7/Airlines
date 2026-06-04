package com.airlines.demo.AirlinesManagement.component;

import com.airlines.demo.AirlinesManagement.model.Flight;
import com.airlines.demo.AirlinesManagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@EnableRetry
public class FlightStatusScheduler {

    @Autowired
    FlightRepository flightRepository;

    @Scheduled(fixedRate = 600000)
    public void updateStatus(){
        List<Flight> flights = flightRepository.findAll();
        LocalTime now = LocalTime.now();

        for(Flight flight : flights){
            if(now.isAfter(flight.getScheduledArrival())){
                flight.setStatus("ARRIVED");
            }
            else if(now.isAfter(flight.getScheduledDeparture())){
                flight.setStatus("DEPARTED");
            }
            else if (now.plusMinutes(90).isBefore(flight.getScheduledDeparture())) {
                flight.setStatus("BOARDING");
            }
            else {
                flight.setStatus("SCHEDULED");
            }
        }
        flightRepository.saveAll(flights);
    }
}
