package com.gabs_desafio_btg.service;

import com.gabs_desafio_btg.DTOs.ApiResponse;
import com.gabs_desafio_btg.DTOs.OrderCreatedEvent;
import com.gabs_desafio_btg.DTOs.OrderResponse;
import com.gabs_desafio_btg.entity.OrderEntity;
import com.gabs_desafio_btg.entity.OrderItem;
import com.gabs_desafio_btg.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Page<OrderResponse> findAllCustomerId(Long customerId, PageRequest pageRequest) {

        var orders =  repository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);

    }

    public void save(OrderCreatedEvent createdEvent) {

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(createdEvent.codigoPedido());
        orderEntity.setCustomerId(createdEvent.codigoCliente());
        orderEntity.setTotal(getTotal(createdEvent));
        orderEntity.setOrderItems(getOrdersItens(createdEvent));

        repository.save(orderEntity);

    }

    private BigDecimal getTotal(OrderCreatedEvent createdEvent) {
        return createdEvent.itens().stream().map(
                item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrdersItens(OrderCreatedEvent listaDePedidos) {
        return listaDePedidos.itens().stream().map(item -> new OrderItem(item.produto(), item.quantidade(), item.preco())).toList();
    }

}
