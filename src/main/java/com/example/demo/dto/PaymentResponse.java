package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {

    private Double amount;
    private Double vat;
    private Double platformFee;
    private Double merchantReceives;
}