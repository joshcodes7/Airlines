package com.airlines.demo.AirlinesManagement.repository;

import com.airlines.demo.AirlinesManagement.model.CabinCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabinCrewRepository extends JpaRepository<CabinCrew, Long> {
    List<CabinCrew> findByFlightNumber(Integer flightNumber);

}
