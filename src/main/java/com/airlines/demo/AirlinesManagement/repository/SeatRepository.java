package com.airlines.demo.AirlinesManagement.repository;

import com.airlines.demo.AirlinesManagement.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
