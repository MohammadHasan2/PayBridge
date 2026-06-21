package com.example.demo.controller;

import com.example.demo.dto.InvoiceResponse;
import com.example.demo.model.Invoice;
import com.example.demo.model.Transaction;
import com.example.demo.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;

    @GetMapping("/{id}")
    public InvoiceResponse getInvoice(
            @PathVariable Long id) {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow();

        Transaction tx =
                invoice.getTransaction();

        return new InvoiceResponse(
                invoice.getInvoiceNumber(),
                tx.getAmount(),
                tx.getVatAmount(),
                tx.getPlatformFee(),
                tx.getMerchantAmount()
        );
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<Resource> downloadPdf(
        @PathVariable Long id)
        throws Exception {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow();

        Path path =
                Paths.get(
                        invoice.getPdfPath()
                );

        Resource resource =
                new UrlResource(
                        path.toUri()
                );

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename="
                                + path.getFileName()
                )
                .body(resource);
        }
}