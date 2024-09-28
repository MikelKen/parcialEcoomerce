package com.parcial.backend.model.service;



import org.springframework.stereotype.Service;
import java.util.List;

import com.parcial.backend.model.DTO.CategoryDTO;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.entity.CartProduct;
import com.parcial.backend.model.entity.Product;
import com.parcial.backend.model.repository.CartRepository;
import com.parcial.backend.model.repository.ProductRepository;

import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
    
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public UserDTO addToCart(CategoryDTO cartProduct,HttpServletRequest request){
        
        try {
            Integer userId = (Integer) request.getAttribute("userId");
            Integer productId  = cartProduct.getProductId();
            

          
            CartProduct existCartProduct = cartRepository.findByUserIdAndProductId(userId, productId);
             System.out.println("Existe EL PROCUTO "+existCartProduct);

            if(existCartProduct != null){
                return UserDTO.builder()
                    .data(null)
                    .success(false)
                    .error(true)
                    .message("Already exets in add to cart")
                    .build();
            }


            Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            CartProduct newCartProduct = CartProduct.builder()
                .product(product)
                .quantity(1)
                .userId(userId)
                .build();

            CartProduct saveCartProduct = cartRepository.save(newCartProduct);
            return UserDTO.builder()
            .data(saveCartProduct)
            .success(true)
            .error(false)
            .message("Product Added in Cart")
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

    public UserDTO countAddToCartProduct(HttpServletRequest request){
        
        try {

            Integer userId = (Integer) request.getAttribute("userId");
            
            List<CartProduct> allProducts = cartRepository.findByUserId(userId);
            
            Integer count = allProducts.size();
            return UserDTO.builder()
            .data(Map.of("count",count))
            .success(true)
            .error(false)
            .message("ok")
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

    public UserDTO updateAddToCartProduct(CategoryDTO cartProduct,HttpServletRequest request){
        
        try {

            Integer userId = (Integer) request.getAttribute("userId");
            Integer productId = cartProduct.get_id();
            Integer quantity = cartProduct.getQuantity();



            CartProduct existCartProduct = cartRepository.findByUserIdAndProductId(userId, productId);

            if(existCartProduct != null){
                existCartProduct.setQuantity(quantity);
                cartRepository.save(existCartProduct);

                return UserDTO.builder()
                    .data(existCartProduct)
                    .success(true)
                    .error(false)
                    .message("Product Updated")
                    .build();
            }else{
                return UserDTO.builder()
                    .data(null)
                    .success(false)
                    .error(true)
                    .message("Product not found")
                    .build();
            }

        } catch (Exception e) {
           return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }     
    }

    public UserDTO deleteAddToCartProduct(CategoryDTO cartProduct,HttpServletRequest request){
        
        try {

            Integer userId = (Integer) request.getAttribute("userId");
            Integer productId = cartProduct.get_id();
            CartProduct existcartProduct = cartRepository.findByUserIdAndProductId(userId, productId);

            if(existcartProduct!= null){
                cartRepository.delete(existcartProduct);
                return UserDTO.builder()
                    .data(null)
                    .success(true)
                    .error(false)
                    .message("Delete Product")
                    .build();
            }else{
                return UserDTO.builder()
                .data(null)
                .success(false)
                .error(true)
                .message("Product not found")
                .build();
            }           
            

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
