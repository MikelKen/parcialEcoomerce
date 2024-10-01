package com.parcial.backend.model.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.parcial.backend.model.DTO.FilterDTO;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.controller.userController;
import com.parcial.backend.model.entity.Product;
import com.parcial.backend.model.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SearchService {
    
    private final ProductRepository productRepository;

    public FilterDTO filterProduct (List<String> categoryList ){
        try {
          
          if(categoryList == null || categoryList.isEmpty()){
            return FilterDTO.builder()
                .data(null)
                .message("Category list cannot be null")
                .error(true)
                .success(false)
                .build();
          }


          List<Product> products = productRepository.findByCategoryIn(categoryList);

            return FilterDTO.builder()
                .data(products)
                .success(true)
                .error(false)
                .message("Succesfull payment")
                .build();
        } catch (Exception e) {
            return FilterDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }
    }

    public UserDTO search (String query ){

            try {
                List<Product> products = productRepository.searchProductByQuery(query);
                return UserDTO.builder()
                    .data(products)
                    .success(true)
                    .error(false)
                    .message("Search !!!!!")
                    .build();
            } catch (Exception e) {
                return UserDTO.builder()
                .data(null)
                .success(false)
                .error(true)
                .message(e.getMessage())
                .build();
            }
    }
}
