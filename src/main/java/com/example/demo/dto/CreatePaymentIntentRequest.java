package com.example.demo.dto;

import lombok.Data;

@Data
public class CreatePaymentIntentRequest {

    private Long merchantId;
    private Double amount;
}