package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.dto.CabinCrewDto;
import com.airlines.demo.AirlinesManagement.dto.FlightDto;
import com.airlines.demo.AirlinesManagement.exception.FlightNotFoundException;
import com.airlines.demo.AirlinesManagement.model.CabinCrew;
import com.airlines.demo.AirlinesManagement.model.Flight;
import com.airlines.demo.AirlinesManagement.model.FlightStatus;
import com.airlines.demo.AirlinesManagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    private CabinCrewService cabinCrewService;

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public void addFlight(List<Flight> fli){
        for (Flight flight : fli) {
            flight.setFlightStatus(FlightStatus.OT);
        }
        flightRepository.saveAll(fli);
    }

    public Flight modifyFlight(Long uid, Flight flight){
        return flightRepository.findById(uid).map(existf -> {
            existf.setDepartureDate(flight.getDepartureDate());
            existf.setArrivalDate(flight.getArrivalDate());
            existf.setArrival(flight.getArrival());
            existf.setDestination(flight.getDestination());
            existf.setScheduledArrival(flight.getScheduledArrival());
            existf.setScheduledDeparture(flight.getScheduledDeparture());
            return flightRepository.save(existf);
        })
                .orElseThrow(() -> new FlightNotFoundException("Flight not found for this id: " + uid));
    }

    public String deleteFlight(Long uid){
        flightRepository.deleteById(uid);
        return "Flight has been deleted successfully";
    }

    public Flight changeStatus(Long uid, FlightStatus fs){
        Flight fl = flightRepository.findById(uid).orElseThrow(() -> new FlightNotFoundException("No flight exists for the id: " +uid));
        fl.setFlightStatus(fs);
        return flightRepository.save(fl);
    }

    public Flight delayFlight(Long uid, LocalTime ed, LocalTime ea){
        Flight fl = flightRepository.findById(uid).orElseThrow(() -> new FlightNotFoundException("No flight exists with this flight id: "+ uid));
        fl.setEstimatedDeparture(ed);
        fl.setEstimatedDeparture(ea);
        fl.setFlightStatus(FlightStatus.DEL);
        return flightRepository.save(fl);
    }

    public FlightDto getCrewForFlight(Integer flightNumber){
        List<Flight> flight = flightRepository.findByFlightNumber(flightNumber);
        FlightDto dto = new FlightDto();
        for(Flight fl:flight){
            dto.setArrival(fl.getArrival());
            dto.setDestination(fl.getDestination());
            dto.setFlightStatus(fl.getFlightStatus());
            dto.setArrivalDate(fl.getArrivalDate());
            dto.setDepartureDate(fl.getDepartureDate());
        }
        List<CabinCrewDto> crews = cabinCrewService.importCrew(flightNumber);
        dto.setCabinCrews(crews);
        return dto;
    }
}
