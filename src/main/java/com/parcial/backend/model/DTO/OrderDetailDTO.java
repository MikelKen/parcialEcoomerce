package com.parcial.backend.model.DTO;



import java.util.List;

import com.parcial.backend.model.entity.OrderItem;

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
    private String userName;
    private Integer quantity;
    private Integer orderId;
    private String paymentMethod;
    private ProductDTO product;
    private Double sellingPrice;
    private List<OrderItemDTO> items;
    private Integer total;
    private String state;

}
