package com.example.demo.service;

import com.example.demo.dto.MerchantRequest;
import com.example.demo.model.Merchant;
import com.example.demo.model.User;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final UserRepository userRepository;
    private final VatService vatService;

    public Merchant createMerchant(
            MerchantRequest request,
            String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Merchant merchant = new Merchant();

        merchant.setBusinessName(request.getBusinessName());
        merchant.setCountry(request.getCountry());
        merchant.setEmail(request.getEmail());

        merchant.setVatRate(
                vatService.getVatRate(request.getCountry())
        );

        merchant.setUser(user);

        return merchantRepository.save(merchant);
    }
}