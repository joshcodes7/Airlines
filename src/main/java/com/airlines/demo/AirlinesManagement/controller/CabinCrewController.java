package com.airlines.demo.AirlinesManagement.controller;

import com.airlines.demo.AirlinesManagement.model.CabinCrew;
import com.airlines.demo.AirlinesManagement.service.CabinCrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flightadmin")
public class CabinCrewController {

    @Autowired
    CabinCrewService cabinCrewService;

    @GetMapping("/allCrews")
    public List<CabinCrew> allCrew(){
        return cabinCrewService.allCrews();
    }

    @PostMapping("/addCabinCrew")
    public CabinCrew addCrew(@RequestBody CabinCrew cabinCrew){
        return cabinCrewService.addCabinCrew(cabinCrew);
    }

    @PutMapping("/updateCrew")
    public CabinCrew updateCrew(@PathVariable Long uid, @RequestBody CabinCrew crew){
        return cabinCrewService.updateCabinCrew(uid, crew);
    }

}
