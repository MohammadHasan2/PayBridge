package com.example.demo.controller;

import com.example.demo.service.StripeWebhookService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stripe")
@RequiredArgsConstructor
public class StripeWebhookController {

    private final StripeWebhookService webhookService;

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping("/webhook")
    public String handleWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature")
            String signature) {
        System.out.println("WEBHOOK RECEIVED");
        Event event;

        try {

            event =
                    Webhook.constructEvent(
                            payload,
                            signature,
                            endpointSecret);

        } catch (SignatureVerificationException e) {

            return "Invalid Signature";
        }

        if ("payment_intent.succeeded"
                .equals(event.getType())) {
            System.out.println("EVENT TYPE = " + event.getType());
            System.out.println("PAYMENT SUCCEEDED EVENT");
            PaymentIntent paymentIntent =
                    (PaymentIntent)
                            event.getDataObjectDeserializer()
                                    .getObject()
                                    .orElse(null);
                System.out.println("PAYMENT INTENT = " + paymentIntent);
            if (paymentIntent != null) {
                System.out.println("METADATA = " + paymentIntent.getMetadata());
                Long merchantId =
                        Long.parseLong(
                                paymentIntent
                                        .getMetadata()
                                        .get("merchantId"));

                Double amount =
                        paymentIntent.getAmount()
                                / 100.0;
                System.out.println("MERCHANT ID = " + merchantId);
                System.out.println("AMOUNT = " + amount);
                webhookService
                        .handleSuccessfulPayment(
                            event.getId(),
                            merchantId,
                            amount);
            }
        }
        System.out.println("WEBHOOK CONTROLLER HIT: " + event.getId());
        return "Success";
    }
}