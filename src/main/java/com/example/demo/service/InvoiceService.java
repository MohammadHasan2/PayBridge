package com.example.demo.service;

import com.example.demo.model.Invoice;
import com.example.demo.model.Transaction;
import com.example.demo.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoicePdfService invoicePdfService;

    @Async
    public void createInvoiceAsync(Transaction transaction) {

        try {
            Invoice invoice = new Invoice();

            invoice.setInvoiceNumber(UUID.randomUUID().toString());
            invoice.setCreatedAt(LocalDateTime.now());
            invoice.setTransaction(transaction);

            invoiceRepository.save(invoice);

            String pdfPath = invoicePdfService.generateInvoicePdf(invoice);

            invoice.setPdfPath(pdfPath);

            invoiceRepository.save(invoice);

        } catch (Exception e) {
            System.out.println("Invoice/PDF failed: " + e.getMessage());
        }
    }
}