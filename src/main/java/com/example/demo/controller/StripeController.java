package com.example.demo.controller;

import com.example.demo.dto.CreatePaymentIntentRequest;
import com.example.demo.dto.CreatePaymentIntentResponse;
import com.example.demo.service.StripePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stripe")
@RequiredArgsConstructor
public class StripeController {

    private final StripePaymentService stripePaymentService;
    @PostMapping("/create-intent")
    public CreatePaymentIntentResponse createIntent(
            @RequestBody CreatePaymentIntentRequest request)
            throws Exception {

        return stripePaymentService
                .createPaymentIntent(request);
    }
    
}