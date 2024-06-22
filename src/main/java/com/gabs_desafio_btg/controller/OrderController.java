package com.gabs_desafio_btg.controller;

import com.gabs_desafio_btg.DTOs.ApiResponse;
import com.gabs_desafio_btg.DTOs.OrderResponse;
import com.gabs_desafio_btg.DTOs.PaginationResponse;
import com.gabs_desafio_btg.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@PathVariable("customerId") Long customerId,
                                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageResponse = service.findAllCustomerId(customerId, PageRequest.of(page,pageSize));

        return ResponseEntity.ok(new ApiResponse<>(
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }

}
