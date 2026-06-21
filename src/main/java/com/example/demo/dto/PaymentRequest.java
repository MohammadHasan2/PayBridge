package com.example.demo.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long merchantId;

    private Double amount;
}