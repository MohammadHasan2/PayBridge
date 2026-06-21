package com.example.demo.service;

import com.example.demo.dto.MerchantReportResponse;
import com.example.demo.model.Merchant;
import com.example.demo.model.Transaction;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TransactionRepository transactionRepository;
    private final MerchantRepository merchantRepository;

    public MerchantReportResponse getCurrentMerchantReport(
        String email) {

    Merchant merchant =
            merchantRepository.findByUserEmail(email)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Merchant not found"));

    List<Transaction> transactions =
            transactionRepository.findByMerchantId(
                    merchant.getId());

    double totalProcessed = transactions.stream()
            .mapToDouble(Transaction::getAmount)
            .sum();

    double totalVat = transactions.stream()
            .mapToDouble(Transaction::getVatAmount)
            .sum();

    double totalFees = transactions.stream()
            .mapToDouble(Transaction::getPlatformFee)
            .sum();

    double totalMerchantRevenue = transactions.stream()
            .mapToDouble(Transaction::getMerchantAmount)
            .sum();

    return new MerchantReportResponse(
            totalProcessed,
            totalVat,
            totalFees,
            totalMerchantRevenue
    );
}
}