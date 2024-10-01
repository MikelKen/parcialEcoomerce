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
public class OrderItemDTO {
    private Integer id;
    private String name;
    private String category;
    private Integer quantity;
    private Double price;
    private List<String> productImage;
}
