package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class VatService {

    public double getVatRate(String country) {

        return switch (country.toLowerCase()) {

            case "lebanon" -> 11.0;
            case "france" -> 20.0;
            case "germany" -> 19.0;
            case "uae" -> 5.0;

            default -> 15.0;
        };
    }
}