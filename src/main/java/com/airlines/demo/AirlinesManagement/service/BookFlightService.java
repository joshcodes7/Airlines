package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.model.BookFlight;
import com.airlines.demo.AirlinesManagement.model.Flight;
import com.airlines.demo.AirlinesManagement.model.Payment;
import com.airlines.demo.AirlinesManagement.repository.BookFlightRepository;
import com.airlines.demo.AirlinesManagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFlightService {

    @Autowired
    BookFlightRepository userRepo;

    @Autowired
    FlightRepository flightRepository;

    public List<BookFlight> getAllId(){
        return userRepo.findAll();
    }

    public BookFlight getById(Long id){
        return userRepo.findById(id).orElse(new BookFlight());
    }

    public List<BookFlight> getUserBookingByFlightNumber(Integer fno){
        return userRepo.findByFlightNumber(fno);
    }

    public BookFlight updateBooking(BookFlight bf, Long id){
        return userRepo.findById(id)
                .map(existbf -> {
                    List<Flight> flight = flightRepository.findByFlightNumber(bf.getFlightNumber());
                    if(flight.isEmpty()){
                        throw new RuntimeException("Flight not found");
                    }
                    Flight matched = null;
                    for(Flight f: flight){
                        if(f.getDestination().equals(bf.getDepartureAirport()) && f.getArrival().equals(bf.getArrivalAirport()) && f.getDepartureDate().equals(bf.getDepartureDate()) && f.getArrivalDate().equals(bf.getReturnDate())){
                            matched = f;
                            break;
                        }
                    }
                    if (matched == null) {
                        throw new RuntimeException("Invalid flight details");
                    }

                    existbf.setFlightNumber(bf.getFlightNumber());
                    existbf.setDepartureDate(bf.getDepartureDate());
                    existbf.setDepartureAirport(bf.getDepartureAirport());
                    existbf.setArrivalAirport(bf.getArrivalAirport());
                    existbf.setReturnDate(bf.getReturnDate());
                    return userRepo.save(existbf);
                })
                .orElseThrow(() -> new RuntimeException("Booking not found for UID: " + id));
    }

    public void addBooking(List<BookFlight> bookings) {
        if (bookings.size() == 1 && bookings.get(0).getAge() < 14) {
            throw new IllegalArgumentException("Underaged passenger requires a companion.");
        }
        boolean hasAdult = false;
        for(BookFlight book : bookings){
            if(book.getAge() > 14){
                hasAdult = true;
                break;
            }
        }
        if(!hasAdult){
            throw new RuntimeException("At least one adult passenger is required");
        }
        Integer fno = bookings.get(0).getFlightNumber();
        List<Flight> flights = flightRepository.findByFlightNumber(fno);

        if(flights.isEmpty()){
            throw new RuntimeException("Flight is not present");
        }
        Flight matched = null;
        for(Flight f: flights){
            if(f.getDestination().equals(bookings.get(0).getDepartureAirport()) &&
            f.getArrival().equals(bookings.get(0).getArrivalAirport()) &&
            f.getDepartureDate().equals(bookings.get(0).getDepartureDate()) &&
            f.getArrivalDate().equals(bookings.get(0).getReturnDate())){
                matched = f;
                break;
            }
        }
        if(matched == null){
            throw new RuntimeException("There is no flight available for the details provided.");
        }
        bookSeats(matched);

        double payment = 0.0;
        for(BookFlight book: bookings){
            if(book.getAge() > 14){
                payment += 70.0;
            }
            else if(book.getAge() <= 14 && book.getAge() > 5){
                payment += 50.0;
            }
            else{
                payment+=25.0;
            }
        }
        Payment pay = new Payment();
        pay.setAmount(payment);
        pay.setStatus("SUCCESS");
        for (BookFlight b : bookings) {
            b.setFlight(matched);
            b.setPayment(pay);
        }

        userRepo.saveAll(bookings);
    }

    public void deleteBooking(Long id){
        userRepo.deleteById(id);
    }

    public synchronized static void bookSeats(Flight flight){
        int seats = flight.getSeats();
        if(seats<=0){
            throw new RuntimeException("No seats available. the last seat has been booked.");
        }
        flight.setSeats(seats - 1);

    }
}
