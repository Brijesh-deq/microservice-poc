package com.order_service.order_service.service;

import com.order_service.order_service.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    public List<Order> getAllOrder();
    public Order getOrderById(Long orderId);
    public Long saveOrder(Order order);
}
