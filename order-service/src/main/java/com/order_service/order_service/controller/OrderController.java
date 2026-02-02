package com.order_service.order_service.controller;

import com.order_service.order_service.dto.OrderDto;
import com.order_service.order_service.dto.OrderResponse;
import com.order_service.order_service.dto.UserDto;
import com.order_service.order_service.entity.Order;
import com.order_service.order_service.service.OrderService;
import com.order_service.order_service.userService.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserClient userClient;

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    // Save order
    @PostMapping
    public ResponseEntity<Long> saveOrder(@RequestBody OrderDto orderDto) {
        Order order = objectMapper.convertValue(orderDto,Order.class);
        Long orderId = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping("/orderDetails/{orderId}")
    public ResponseEntity<OrderResponse> getOrderWithUserDetailsByID(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        OrderDto orderDto = objectMapper.convertValue(order,OrderDto.class);
        ResponseEntity<List<UserDto>> response = userClient.getUserById(Collections.singletonList(order.getUserId()));
         List<UserDto> userDtos = response.getBody();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderDto(orderDto);
        orderResponse.setUserDtos(userDtos);
        return ResponseEntity.ok(orderResponse);
    }
}


