package com.parcial.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.backend.model.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Integer>{
    PaymentMethod findByName(String name);
}
