package com.gabs_desafio_btg.listener;

import com.gabs_desafio_btg.DTOs.OrderCreatedEvent;
import com.gabs_desafio_btg.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.gabs_desafio_btg.config.RabbitMQConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService service;

    public OrderCreatedListener(OrderService service) {
        this.service = service;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){

        logger.info("Info do que vem da fila {}", message.getPayload());

        service.save(message.getPayload());

    }

}
