package com.airlines.demo.AirlinesManagement.controller;

import com.airlines.demo.AirlinesManagement.model.BookFlight;
import com.airlines.demo.AirlinesManagement.service.BookFlightService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@RestController
public class BookFlightController {

    @Autowired
    BookFlightService bookFlightService;

    private static final Logger logger = LoggerFactory.getLogger(BookFlightController.class);

    @GetMapping("/flightadmin/allBooking")
    public List<BookFlight> getAllBooking(){
        return bookFlightService.getAllId();
    }

    @PostMapping("/flight/booking")
    public void addBooking(@RequestBody List<BookFlight> bf){
        bookFlightService.addBooking(bf);
    }

    @PutMapping("/flight/updateBooking/{uid}")
    public void updateBooking(@PathVariable Long uid, @RequestBody BookFlight bf){
        bookFlightService.updateBooking(bf, uid);
        logger.info("Succesfully updated");
    }

    @DeleteMapping("/flight/deleteBooking/{uid}")
    public void deleteBooking(@PathVariable Long uid){
        bookFlightService.deleteBooking(uid);
    }


}
