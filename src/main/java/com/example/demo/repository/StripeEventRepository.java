package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.StripeEvent;

public interface StripeEventRepository extends JpaRepository<StripeEvent, String> {
}