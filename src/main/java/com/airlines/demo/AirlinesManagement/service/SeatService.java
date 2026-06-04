package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.model.Seat;
import com.airlines.demo.AirlinesManagement.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public String bookSeats(List<Seat> seats){
        for(Seat seat: seats){
            if(seat.getSeatNUmber()>30) throw new RuntimeException("No such seat number applicable");
            if(seat.getStatus() == "BOOKED") throw new RuntimeException("Seat has already been booked");
        }
        seatRepository.saveAll(seats);
        return "The seats have been booked";
    }
}
