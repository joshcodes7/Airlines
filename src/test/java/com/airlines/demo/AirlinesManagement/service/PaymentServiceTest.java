package com.airlines.demo.AirlinesManagement.service;

import com.airlines.demo.AirlinesManagement.model.Payment;
import com.airlines.demo.AirlinesManagement.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentService paymentService;

    @Test
    void testAddPayment() {
        Payment p1 = new Payment();
        Payment p2 = new Payment();

        List<Payment> paymentList = Arrays.asList(p1, p2);

        when(paymentRepository.findAll()).thenReturn(paymentList);
        List<Payment> result = paymentService.addPayment();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(paymentRepository, times(1))
                .findAll();
    }
}
