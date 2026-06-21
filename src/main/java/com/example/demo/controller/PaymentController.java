package com.example.demo.controller;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    
    @Operation(
        summary = "Create Payment",
        description = "Processes merchant payment and calculates VAT"
    )
    @PostMapping
    public PaymentResponse processPayment(
            @RequestBody PaymentRequest request) {

        return paymentService.processPayment(request);
    }
}