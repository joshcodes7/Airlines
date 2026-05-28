package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.model.Flight;
import com.airlines.demo.AirlinesManagement.model.FlightStatus;
import com.airlines.demo.AirlinesManagement.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @Mock
    CabinCrewService cabinCrewService;

    @InjectMocks
    FlightService flightService;

    @Test
    void getAllFlight(){
        Flight f1 = new Flight();
        Flight f2 = new Flight();
        List<Flight> flightList = Arrays.asList(f1,f2);
        when(flightRepository.findAll()).thenReturn(flightList);

        List<Flight> result = flightService.getAllFlights();

        assertEquals(2, result.size());
        assertNotNull(result);
        verify(flightRepository, times(1)).findAll();
    }

    @Test
    void addFlightTest(){
        Flight f1 = new Flight();
        Flight f2 = new Flight();
        List<Flight> flightList = Arrays.asList(f1,f2);
        when(flightRepository.saveAll(flightList)).thenReturn(flightList);

        flightService.addFlight(flightList);
        assertEquals(FlightStatus.OT, f1.getFlightStatus());
        assertEquals(FlightStatus.OT, f2.getFlightStatus());

        verify(flightRepository, times(1)).saveAll(flightList);
    }

    @Test
    void modifyFlightTest(){
        Long uid = 1L;
        Flight flight = new Flight();
        flight.setArrival("SFB");
        flight.setDestination("LAS");
        flight.setScheduledDeparture(LocalTime.now());
        flight.setScheduledArrival(LocalTime.now());
        when(flightRepository.findById(uid)).thenReturn(Optional.of(flight));
        when(flightRepository.save(flight)).thenReturn(flight);

        Flight newFlight = flightService.modifyFlight(uid, flight);
        assertNotNull(flight);
        verify(flightRepository, times(1)).findById(uid);
        verify(flightRepository, times(1)).save(flight);
    }
}
