package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantReportResponse {

    private Double totalProcessed;
    private Double totalVat;
    private Double totalPlatformFees;
    private Double totalMerchantRevenue;
}