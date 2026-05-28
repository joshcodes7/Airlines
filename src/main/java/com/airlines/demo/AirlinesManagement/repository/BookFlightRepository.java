package com.airlines.demo.AirlinesManagement.repository;

import com.airlines.demo.AirlinesManagement.model.BookFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookFlightRepository extends JpaRepository<BookFlight, Long> {
    public List<BookFlight> findByFlightNumber(Integer flightNumber);
}
