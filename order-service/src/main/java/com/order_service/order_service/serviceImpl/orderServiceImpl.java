package com.order_service.order_service.serviceImpl;

import com.order_service.order_service.entity.Order;
import com.order_service.order_service.event.OrderCreatedEvent;
import com.order_service.order_service.event.OrderProducer;
import com.order_service.order_service.repository.OrderRepo;
import com.order_service.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderServiceImpl implements OrderService {

    private OrderRepo orderRepo;
    private OrderProducer orderProducer;
    @Autowired
    public orderServiceImpl(OrderRepo orderRepo,OrderProducer orderProducer)
    {
        this.orderRepo = orderRepo;
        this.orderProducer = orderProducer;
    }
    @Override
    public List<Order> getAllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepo.findById(orderId).get();
    }

    @Override
    public Long saveOrder(Order order) {
        Order savedOrder = orderRepo.save(order);
        orderProducer.send(new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getProduct()
        ));

        return savedOrder.getId();
    }
}
