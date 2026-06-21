package com.example.demo.controller;

import com.example.demo.dto.MerchantRequest;
import com.example.demo.model.Merchant;
import com.example.demo.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/register")
    public Merchant registerMerchant(
            @RequestBody MerchantRequest request,
            Authentication authentication) {

        return merchantService.createMerchant(
                request,
                authentication.getName()
        );
    }
}