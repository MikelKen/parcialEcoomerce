package com.parcial.backend.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.backend.model.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

    List<Orders> findByState(String state);

    List<Orders> findByUserId(Integer userId);
}
