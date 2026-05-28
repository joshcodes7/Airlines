package com.airlines.demo.AirlinesManagement.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    private Integer flightNumber;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Integer seats;
    private String arrival;
    private String destination;
    private LocalTime scheduledDeparture;
    private LocalTime scheduledArrival;
    private LocalTime estimatedDeparture;
    private LocalTime estimatedArrival;
    private String status;

    @OneToMany(mappedBy = "flight")
    List<BookFlight> bf;

    @Enumerated
    FlightStatus flightStatus;

    @OneToMany(mappedBy = "flightCrew")
    List<CabinCrew> cabinCrews;

    public List<CabinCrew> getCabinCrews() {
        return cabinCrews;
    }

    public void setCabinCrews(List<CabinCrew> cabinCrews) {
        this.cabinCrews = cabinCrews;
    }

    public List<BookFlight> getBf() {
        return bf;
    }

    public void setBf(List<BookFlight> bf) {
        this.bf = bf;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
