package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Double vatAmount;

    private Double platformFee;

    private Double merchantAmount;

    private String status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;
}