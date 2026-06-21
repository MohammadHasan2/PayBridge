package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceResponse {

    private String invoiceNumber;

    private Double amount;

    private Double vat;

    private Double platformFee;

    private Double merchantReceives;
}
