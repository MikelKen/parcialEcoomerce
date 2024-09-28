package com.parcial.backend.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.backend.model.DTO.CategoryDTO;

import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class cartController {
    
    @Autowired
    private final CartService cartService;

    @PostMapping("/addtocart")
    public ResponseEntity<UserDTO> addtocart(@RequestBody CategoryDTO cartProdut,HttpServletRequest request ){
        return ResponseEntity.ok(cartService.addToCart(cartProdut,request));
    }

    @GetMapping("/countAddToCartProduct")
    public ResponseEntity<UserDTO> countAddToCartProduct( HttpServletRequest request ){
        return ResponseEntity.ok(cartService.countAddToCartProduct( request ));
    }

    @GetMapping("/view-card-product")
    public ResponseEntity<UserDTO> addToCartViewProduct( HttpServletRequest request ){
        return ResponseEntity.ok(cartService.countAddToCartProduct( request ));
    }

    @PostMapping("/delete-cart-product")
    public ResponseEntity<UserDTO> deleteAddToCartProduct(@RequestBody CategoryDTO cartProdut,HttpServletRequest request ){
        return ResponseEntity.ok(cartService.deleteAddToCartProduct(cartProdut,request));
    }
}
