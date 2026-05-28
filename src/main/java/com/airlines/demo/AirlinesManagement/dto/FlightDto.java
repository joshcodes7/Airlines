package com.airlines.demo.AirlinesManagement.dto;

import com.airlines.demo.AirlinesManagement.model.BookFlight;
import com.airlines.demo.AirlinesManagement.model.CabinCrew;
import com.airlines.demo.AirlinesManagement.model.FlightStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class FlightDto {
    private Long flightId;
    private Integer flightNumber;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String arrival;
    private String destination;
    private LocalTime scheduledDeparture;
    private LocalTime scheduledArrival;
    private LocalTime estimatedDeparture;
    private LocalTime estimatedArrival;
    private String status;
    private FlightStatus flightStatus;
    private List<CabinCrewDto> cabinCrews;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public LocalTime getEstimatedDeparture() {
        return estimatedDeparture;
    }

    public void setEstimatedDeparture(LocalTime estimatedDeparture) {
        this.estimatedDeparture = estimatedDeparture;
    }

    public LocalTime getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(LocalTime estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CabinCrewDto> getCabinCrews() {
        return cabinCrews;
    }

    public void setCabinCrews(List<CabinCrewDto> cabinCrews) {
        this.cabinCrews = cabinCrews;
    }
}
