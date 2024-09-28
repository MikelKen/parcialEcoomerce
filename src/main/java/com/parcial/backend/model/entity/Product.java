package com.parcial.backend.model.entity;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _id;

    private String productName;
    private String brandName; 

    @ElementCollection
    private List<String> productImage;
    private String description;

    private String category;
    
    private Double price; 
    private Double sellingPrice; 

    private LocalTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalTime.now();
    }

}
