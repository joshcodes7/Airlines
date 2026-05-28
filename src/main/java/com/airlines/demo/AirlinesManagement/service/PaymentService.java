package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.model.Payment;
import com.airlines.demo.AirlinesManagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> addPayment(){
        return paymentRepository.findAll();
    }
}
