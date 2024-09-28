package com.parcial.backend.model.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parcial.backend.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByCategory(String category);

    List<Product> findAllByOrderByCreatedAtDesc();
    

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();

    
}
