package com.parcial.backend.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.parcial.backend.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{

    List<OrderItem> findByOrderId(Integer orderId);
    
}
