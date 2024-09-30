package com.parcial.backend.model.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    
    private String id;

    private Integer amount;

    private List<OrderDetailDTO> productDetail;

    private String paymentMethod;
    private String state;

    private Integer totalQty;

}
