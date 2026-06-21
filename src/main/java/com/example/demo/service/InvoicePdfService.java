package com.example.demo.service;

import com.example.demo.model.Invoice;
import com.example.demo.model.Transaction;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class InvoicePdfService {

    public String generateInvoicePdf(
            Invoice invoice) {

        try {

            String fileName =
                    "invoice-" +
                    invoice.getInvoiceNumber() +
                    ".pdf";

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(fileName)
            );

            document.open();

            Transaction tx =
                    invoice.getTransaction();

            document.add(
                    new Paragraph(
                            "PAYMENT GATEWAY INVOICE"
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(
                            "Invoice Number: "
                            + invoice.getInvoiceNumber()
                    )
            );

            document.add(
                    new Paragraph(
                            "Amount: $"
                            + tx.getAmount()
                    )
            );

            document.add(
                    new Paragraph(
                            "VAT: $"
                            + tx.getVatAmount()
                    )
            );

            document.add(
                    new Paragraph(
                            "Platform Fee: $"
                            + tx.getPlatformFee()
                    )
            );

            document.add(
                    new Paragraph(
                            "Merchant Receives: $"
                            + tx.getMerchantAmount()
                    )
            );

            document.close();

            return fileName;

        } catch (Exception e) {

            throw new RuntimeException(
                    "PDF generation failed"
            );
        }
    }
}