package com.parcial.backend.model.DTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    public Integer _id;
    private String productName;
    private String brandName;
    private String category;
    private List<String> productImage;
    private String description;
    private Double price;
    private Double sellingPrice;
}
