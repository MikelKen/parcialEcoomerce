package com.parcial.backend.model.DTO;

import java.util.List;

import com.parcial.backend.model.entity.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterDTO {
    private List<Product> data;
    private boolean success;
    private boolean error;
    private String message;
}
