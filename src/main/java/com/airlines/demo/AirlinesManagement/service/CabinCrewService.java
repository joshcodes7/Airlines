package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.dto.CabinCrewDto;
import com.airlines.demo.AirlinesManagement.model.CabinCrew;
import com.airlines.demo.AirlinesManagement.model.Flight;
import com.airlines.demo.AirlinesManagement.repository.CabinCrewRepository;
import com.airlines.demo.AirlinesManagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabinCrewService {

    @Autowired
    CabinCrewRepository cabinCrewRepository;

    @Autowired
    FlightRepository flightRepository;

    public List<CabinCrew> allCrews(){
        return cabinCrewRepository.findAll();
    }

    public CabinCrew addCabinCrew(CabinCrew cc){
        Integer fno = cc.getFlightNumber();
        List<Flight> fl = flightRepository.findByFlightNumber(fno);

        if(fl.isEmpty()){
            throw new RuntimeException("Flight does not exist");
        }
        return cabinCrewRepository.save(cc);
    }

    public CabinCrew updateCabinCrew(Long uid, CabinCrew crew){
        return cabinCrewRepository.findById(uid).map(existcc -> {
            Integer fno = crew.getFlightNumber();
            List<Flight> fl = flightRepository.findByFlightNumber(fno);

            if(fl.isEmpty()){
                throw new RuntimeException("Flight does not exist");
                    }
            existcc.setFlightNumber(crew.getFlightNumber());
            existcc.setPosition(crew.getPosition());
            return cabinCrewRepository.save(crew);
        })
                .orElseThrow(() -> new RuntimeException("Cabin crew details not found for the following uid: " + uid));
    }

    public List<CabinCrewDto> importCrew(Integer flightNumber){
        List<CabinCrew> cabinCrews = cabinCrewRepository.findByFlightNumber(flightNumber);
        return cabinCrews.stream().map(this::mapToDto).toList();
    }

    public CabinCrewDto mapToDto(CabinCrew crew){
        CabinCrewDto dto = new CabinCrewDto();
        dto.setPosition(crew.getPosition());
        dto.setName(crew.getName());
        dto.setFlightNumber(crew.getFlightNumber());

        return dto;
    }
}
