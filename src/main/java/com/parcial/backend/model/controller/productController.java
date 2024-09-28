package com.parcial.backend.model.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.backend.model.DTO.CategoryDTO;
import com.parcial.backend.model.DTO.ProductDTO;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.entity.Product;

import com.parcial.backend.model.service.ProductService;


import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class productController {
    
    private final ProductService productService;



    @PostMapping("/upload-product")
    public ResponseEntity<UserDTO> uploadProduct(@RequestBody ProductDTO request ){
        return ResponseEntity.ok(productService.uploadProduct(request));
    }

    @GetMapping("/get-product")
    public ResponseEntity<UserDTO> getProduct(){
        return ResponseEntity.ok(productService.getProduct());
    }

    @PostMapping("/update-product")
    public ResponseEntity<UserDTO> updateProduct(@RequestBody Product request ){
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @GetMapping("get-categoryProduct")
    public ResponseEntity<UserDTO> getCategoryProduct( ProductDTO request ){
        return ResponseEntity.ok(productService.getCategoryProduct());
    }

    @PostMapping("/category-product")
    public ResponseEntity<UserDTO> cateoryProduct(@RequestBody CategoryDTO request ){
        return ResponseEntity.ok(productService.getCateoryWiseProduct(request));
    }
    @PostMapping("/product-details")
    public ResponseEntity<UserDTO> detailsProduct(@RequestBody CategoryDTO request ){
        return ResponseEntity.ok(productService.getProductDetails(request));
    }

 

  
}
