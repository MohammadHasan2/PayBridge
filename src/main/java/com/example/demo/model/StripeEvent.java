package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stripe_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StripeEvent {

    @Id
    private String eventId; // Stripe event ID (unique)

    private LocalDateTime processedAt;
}
