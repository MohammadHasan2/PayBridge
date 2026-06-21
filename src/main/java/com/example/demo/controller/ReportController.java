package com.example.demo.controller;

import com.example.demo.dto.MerchantReportResponse;
import com.example.demo.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    @Operation(
        summary = "Merchant Report",
        description = "Returns report for authenticated merchant"
    )
    @GetMapping("/me")
    public MerchantReportResponse myReport(
            Authentication authentication) {

        return reportService.getCurrentMerchantReport(
                authentication.getName()
        );
    }
}