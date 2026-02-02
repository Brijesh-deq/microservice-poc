package com.user_service.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {
    @KafkaListener(topics = "order-topic", groupId = "user-group")
    public void consume(OrderCreatedEvent event) {
        System.out.println("Order received for user validation: " + event);
        // validate user, send response if needed
    }
}
