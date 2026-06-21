package com.example.demo.service;

import com.example.demo.dto.CreatePaymentIntentRequest;
import com.example.demo.dto.CreatePaymentIntentResponse;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService {

    public CreatePaymentIntentResponse createPaymentIntent(
            CreatePaymentIntentRequest request)
            throws Exception {

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(
                                (long)(request.getAmount() * 100)
                        )
                        .setCurrency("usd")

                        .putMetadata(
                                "merchantId",
                                request.getMerchantId().toString()
                        )

                        .build();

        PaymentIntent paymentIntent =
                PaymentIntent.create(params);

        return new CreatePaymentIntentResponse(
                paymentIntent.getId(),
                paymentIntent.getClientSecret()
        );
    }
}