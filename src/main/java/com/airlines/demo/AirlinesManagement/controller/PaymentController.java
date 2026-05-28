package com.airlines.demo.AirlinesManagement.controller;

import com.airlines.demo.AirlinesManagement.model.Payment;
import com.airlines.demo.AirlinesManagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/flight/getPayment")
    public List<Payment> addPayment(){
        return paymentService.addPayment();
    }
}
