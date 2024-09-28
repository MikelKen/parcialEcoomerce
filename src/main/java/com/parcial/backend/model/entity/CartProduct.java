package com.parcial.backend.model.entity;



import java.time.LocalTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CartProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "_id")
    private Product product;

    private Integer quantity;

    private Integer userId;

    private LocalTime createdAt;
    
    private LocalTime updateAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalTime.now();
        this.updateAt = LocalTime.now();
    }

    protected void onUpdate(){
        this.updateAt = LocalTime.now();
    }
    
}
