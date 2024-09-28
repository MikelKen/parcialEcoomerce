package com.parcial.backend.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.parcial.backend.model.entity.CartProduct;


public interface CartRepository extends JpaRepository<CartProduct,Integer>{

    List<CartProduct> findByUserId(Integer userId); 

    //CartProduct findByUserIdAndProduct(Integer userId, Product productId);
    @Query("SELECT c FROM CartProduct c WHERE c.userId = :userId AND c.product._id = :productId")
    CartProduct findByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    @Query("SELECT COUNT(c) FROM CartProduct c WHERE c.userId = :userId")
    Integer countByUserId(@Param("userId") Integer userId);

   

} 
