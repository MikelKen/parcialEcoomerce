package com.parcial.backend.model.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parcial.backend.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByCategory(String category);

    List<Product> findAllByOrderByCreatedAtDesc();
    

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.category) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchProductByQuery(@Param("query")String query);

    List<Product> findByCategoryIn(List<String> categoryList);

    
}
