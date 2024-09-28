package com.parcial.backend.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parcial.backend.model.DTO.CategoryDTO;
import com.parcial.backend.model.DTO.ProductDTO;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.entity.Product;
import com.parcial.backend.model.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    
  private final ProductRepository productRepository;


    public UserDTO uploadProduct(ProductDTO request){
        System.out.println("upload se "+request);
        try {
           Product product = Product.builder()
                .productName(request.getProductName())
                .brandName(request.getBrandName())
                .category(request.getCategory())
                .productImage(request.getProductImage())
                .description(request.getDescription())
                .price(request.getPrice())
                .sellingPrice(request.getSellingPrice())
                .build();
            
                Product savedProduct = productRepository.save(product);
            return UserDTO.builder()
            .data(savedProduct)
            .success(true)
            .error(false)
            .message("Product uploaded Successfully!!")
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
    
    public UserDTO getProduct(){
        
        try {
            
            List<Product> allProducts = productRepository.findAllByOrderByCreatedAtDesc();
            return UserDTO.builder()
            .data(allProducts)
            .success(true)
            .error(false)
            .message("All product!!")
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
   
    public UserDTO updateProduct(Product request){
        
        try {
      
            Product updatProduct = productRepository.save((request));
            return UserDTO.builder()
            .data(updatProduct)
            .success(true)
            .error(false)
            .message("Product update Successfully!!")
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
    public UserDTO getCategoryProduct(){
        
        try {
          
           List<String> categories = productRepository.findDistinctCategories();
           
           List<Product> productByCategory = new ArrayList<>();

           for (String category : categories){
            Product product = productRepository.findByCategory(category).stream().findFirst().orElse(null);
            if(product != null){
                productByCategory.add(product);
            }
           }
            
            return UserDTO.builder()
            .data(productByCategory)
            .success(true)
            .error(false)
            .message("Category Product!!")
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
    public UserDTO getCateoryWiseProduct(CategoryDTO request){
        
        try {
            
            String category = request.getCategory();
            List<Product> products = productRepository.findByCategory(category);
         
            return UserDTO.builder()
            .data(products)
            .success(true)
            .error(false)
            .message("Product")
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

    public UserDTO getProductDetails(CategoryDTO request){
        
        try {
            
           Integer productId = request.getProductId();

            Optional<Product> producOptional = productRepository.findById(productId);
            if(producOptional.isPresent()){
                Product product = producOptional.get();
                return UserDTO.builder()
                    .data(product)
                    .success(true)
                    .error(false)
                    .message("ok")
                    .build();
            }
            return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message("Product not found")
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
