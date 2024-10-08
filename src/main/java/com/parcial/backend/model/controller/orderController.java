package com.parcial.backend.model.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.backend.model.DTO.OrderDTO;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class orderController {

    private final OrderService orderService;
    
    @PostMapping("save-order")
    public ResponseEntity<UserDTO> typePaymen(@RequestBody OrderDTO orderReq,HttpServletRequest request ){
     
        return ResponseEntity.ok(orderService.saveOrder(orderReq,request));
    }

    @PostMapping("/save-reserved")
    public ResponseEntity<UserDTO> saveOrder(@RequestBody OrderDTO orderReq,HttpServletRequest request ){
       
        return ResponseEntity.ok(orderService.saveOrder(orderReq,request));
    }

    @GetMapping("/all-orders")
    public ResponseEntity<UserDTO> allOrders(){        
      
        return ResponseEntity.ok(orderService.allOrders());
    }

    @PostMapping("/order-detail")
    public ResponseEntity<UserDTO> orderDetail(@RequestBody Integer orderId){        
      
        return ResponseEntity.ok(orderService.orderDetail(orderId));
    }


     
}
