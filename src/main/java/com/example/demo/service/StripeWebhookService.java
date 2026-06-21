package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.model.Merchant;
import com.example.demo.model.StripeEvent;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.repository.StripeEventRepository;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StripeWebhookService {

    private final MerchantRepository merchantRepository;
    private final TransactionRepository transactionRepository;
    private final InvoiceService invoiceService;
    private final StripeEventRepository stripeEventRepository;

    @Transactional
    public void handleSuccessfulPayment(String eventId, Long merchantId, Double amount) {
         if (stripeEventRepository.existsById(eventId)) {
            System.out.println("DUPLICATE EVENT IGNORED: " + eventId);
            return;
        }

        // 2. MARK EVENT AS PROCESSED
        StripeEvent event = new StripeEvent();
        event.setEventId(eventId);
        event.setProcessedAt(LocalDateTime.now());
        stripeEventRepository.save(event);

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new RuntimeException("Merchant not found"));

        double vat = amount * (merchant.getVatRate() / 100);
        double fee = amount * 0.02;
        double merchantReceives = amount - vat - fee;

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setVatAmount(vat);
        transaction.setPlatformFee(fee);
        transaction.setMerchantAmount(merchantReceives);
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setMerchant(merchant);

        Transaction savedTx = transactionRepository.save(transaction);
        System.out.println("TRANSACTION SAVED ID = " + savedTx.getId());
        // IMPORTANT: async step (NOT transactional)
        invoiceService.createInvoiceAsync(savedTx);
    }
}