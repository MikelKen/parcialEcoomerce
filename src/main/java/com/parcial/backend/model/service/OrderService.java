package com.parcial.backend.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.backend.model.DTO.OrderDTO;
import com.parcial.backend.model.DTO.OrderDetailDTO;
import com.parcial.backend.model.DTO.OrderItemDTO;
import com.parcial.backend.model.DTO.ProductDTO;
import com.parcial.backend.model.DTO.UserDTO;
import com.parcial.backend.model.entity.Orders;
import com.parcial.backend.model.entity.OrderItem;
import com.parcial.backend.model.entity.Users;
import com.parcial.backend.model.repository.OrderItemRepository;
import com.parcial.backend.model.repository.OrderRepository;
import com.parcial.backend.model.repository.UsersRepository;
import com.stripe.param.PaymentIntentCreateParams;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UsersRepository usersRepository;


    public UserDTO saveOrder (OrderDTO orederReq,HttpServletRequest request){

        try {
            Integer userId = (Integer) request.getAttribute("userId");
            String userName = (String) request.getAttribute("username");
           
             System.out.println("::::::::::::::::::"+userName+":::::::::::::"+userId);

            Users user = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
            Orders orders = Orders.builder()
                .user(user)
                .userName(userName)
                .total(orederReq.getAmount())
                .dateOrder(LocalDateTime.now())
                .totalQty(orederReq.getTotalQty())
                .paymentMethod(orederReq.getPaymentMethod())
                .state(orederReq.getState())
                .build();

           Orders saveOrder = orderRepository.save(orders);
     
            saveOrderDetails(orederReq.getProductDetail(), saveOrder);
            return UserDTO.builder()
                .data(saveOrder)
                .success(true)
                .error(false)
                .message("Succesfull payment")
                .build();
        } catch (Exception e) {
            return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }
    }




    public UserDTO saveOrderRev (OrderDTO orederReq,HttpServletRequest request){

        try {
            Integer userId = (Integer) request.getAttribute("userId");
            String userName = (String) request.getAttribute("username");
           
             System.out.println("::::::::::::::::::"+userName+":::::::::::::"+userId);

            Users user = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
            Orders orders = Orders.builder()
                .user(user)
                .userName(userName)
                .total(orederReq.getAmount())
                .dateOrder(LocalDateTime.now())
                .totalQty(orederReq.getTotalQty())
                .paymentMethod(orederReq.getPaymentMethod())
                .state(orederReq.getState())
                .build();

           Orders saveOrder = orderRepository.save(orders);
     
            saveOrderDetails(orederReq.getProductDetail(), saveOrder);
            return UserDTO.builder()
                .data(saveOrder)
                .success(true)
                .error(false)
                .message("Succesfull Reserverd")
                .build();
        } catch (Exception e) {
            return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }
    }



    public void saveOrderDetails(List<OrderDetailDTO> orderDetail, Orders order){
        try {
            System.out.println("ESTE ES TODO ESL PRODUCT:::::::::::::::::::::::: "+orderDetail);
            for(OrderDetailDTO detailDTO : orderDetail){
                ProductDTO productDTO = detailDTO.getProduct();
                System.out.println("ESTE ES LE PRODUTO DTO PARA GUARDAR :::::::::::::::::"+productDTO);
                OrderItem orderItem = OrderItem.builder()
                                    .order(order)
                                    .quantity(detailDTO.getQuantity())
                                    .price(productDTO != null ? productDTO.getSellingPrice():null)
                                    .name(productDTO != null ? productDTO.getProductName():null)
                                    .category(productDTO != null ? productDTO.getCategory():null)
                                    .productImage(productDTO !=null ? productDTO.getProductImage():List.of())
                                    .build();
                orderItemRepository.save(orderItem);
            }

            System.out.println("GUARDARO EL DETALLE: ");
        } catch (Exception e) {
            System.out.println("ERROR : "+e);
        }


    }

    public UserDTO typePayment (OrderDTO request){

        try {
            String id = request.getId();
            int amount = request.getAmount();
    
            System.out.println("el id: "+id+"         el amount"+amount);
    
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long)amount)
                .setCurrency("USD")
                .setPaymentMethod(id)
                .setConfirm(true)
                .build();
    
            System.out.println("haciento prueba "+ params);
            return UserDTO.builder()
                .data(params)
                .success(true)
                .error(false)
                .message("Succesfull payment")
                .build();
        } catch (Exception e) {
            return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }
    }

    public UserDTO allOrders() {  
        try {
        
           List<Orders> allOrders = orderRepository.findAll();
           
            return UserDTO.builder()
            .data(allOrders)
            .success(true)
            .error(false)
            .message("All orders retrieved Successfully!!")
            .build();
        } catch (Exception e) {
           return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }     
    
    }

    public UserDTO orderDetail(Integer orderId) {  
        try {
            
            Orders order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        // Crear la lista de OrderItemDTO
        List<OrderItemDTO> orderItemDTOs = orderItems.stream().map(item -> 
            OrderItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .category(item.getCategory())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .productImage(item.getProductImage()) // Incluir la imagen
                .build()
        ).collect(Collectors.toList());

        // Crear un objeto DTO con los detalles de la orden
        OrderDetailDTO orderDetail = OrderDetailDTO.builder()
            .orderId(order.getId())
            .userName(order.getUserName())
            .total(order.getTotal())
            .paymentMethod(order.getPaymentMethod())
            .state(order.getState())
            .items(orderItemDTOs) // Usar la lista de OrderItemDTO
            .build();

        // Construir la respuesta UserDTO
        return UserDTO.builder()
            .data(orderDetail)
            .success(true)
            .error(false)
            .message("Detalles de la orden recuperados con éxito!")
            .build();
        } catch (Exception e) {
           return UserDTO.builder()
            .data(null)
            .success(false)
            .error(true)
            .message(e.getMessage())
            .build();
        }     
    
    }
}
