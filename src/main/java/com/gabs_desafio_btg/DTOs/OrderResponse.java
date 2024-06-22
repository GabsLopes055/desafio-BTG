package com.gabs_desafio_btg.DTOs;

import com.gabs_desafio_btg.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderid,
                            Long customerId,
                            BigDecimal total) {

    public static OrderResponse fromEntity(OrderEntity entity) {
        return new OrderResponse(entity.getOrderId(), entity.getCustomerId(), entity.getTotal());
    }
}
