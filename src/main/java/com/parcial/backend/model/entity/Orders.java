package com.parcial.backend.model.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Orders {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "id")
    private Users user;

    private String userName;

    private Integer total;

    private LocalDateTime dateOrder;

    private Integer totalQty;

    // @ManyToOne
    // @JoinColumn(name = "paymentMethodId",referencedColumnName = "id")
    private String paymentMethod;

    private String state;




}
