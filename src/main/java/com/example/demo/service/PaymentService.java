package com.example.demo.service;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.model.Invoice;
import com.example.demo.model.Merchant;
import com.example.demo.model.Transaction;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final MerchantRepository merchantRepository;
    private final TransactionRepository transactionRepository;
    private final InvoiceRepository invoiceRepository;

    public PaymentResponse processPayment(
            PaymentRequest request) {

        Merchant merchant =
                merchantRepository.findById(request.getMerchantId())
                .orElseThrow();

        double amount = request.getAmount();

        double vat =
                amount * (merchant.getVatRate() / 100);

        double fee =
                amount * 0.02;

        double merchantReceives =
                amount - vat - fee;

        Transaction transaction =
                new Transaction();

        transaction.setAmount(amount);
        transaction.setVatAmount(vat);
        transaction.setPlatformFee(fee);
        transaction.setMerchantAmount(merchantReceives);
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setMerchant(merchant);

        Transaction savedTx = transactionRepository.save(transaction);

        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber(
                UUID.randomUUID().toString());

        invoice.setCreatedAt(
                LocalDateTime.now());

        invoice.setTransaction(savedTx);

        invoiceRepository.save(invoice);
        return new PaymentResponse(
                amount,
                vat,
                fee,
                merchantReceives
        );
    }
}