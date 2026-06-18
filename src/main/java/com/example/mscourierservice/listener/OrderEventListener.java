package com.example.mscourierservice.listener;

import com.example.mscourierservice.config.RabbitMQConfig;
import com.example.mscourierservice.event.OrderAssignedEvent;
import com.example.mscourierservice.event.OrderDeliveredEvent;
import com.example.mscourierservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final CourierService courierService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_ASSIGNED_QUEUE)
    public void handleOrderAssigned(OrderAssignedEvent event) {
        courierService.markCourierAsOnDelivery(event.getCourierId());
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_DELIVERED_QUEUE)
    public void handleOrderDelivered(OrderDeliveredEvent event) {
        courierService.markCourierAsAvailable(event.getCourierId());
    }
}
