package com.parcial.backend.model.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    
    private Integer id;
    private Integer quantity;
    private Integer orderId;
    private ProductDTO product;
    private Double sellingPrice;


}
