package com.example.mscourierservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeliveredEvent {
    private Long orderId;
    private Long courierId;
    private BigDecimal deliveryPrice;
}
