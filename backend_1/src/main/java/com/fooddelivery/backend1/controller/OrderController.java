package com.fooddelivery.backend1.controller;

import com.fooddelivery.backend1.dto.ApiResponse;
import com.fooddelivery.backend1.model.Order;
import com.fooddelivery.backend1.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<Order>> getOrders(@PathVariable String userId) {
        return ApiResponse.ok("Orders fetched", orderService.getOrders(userId));
    }
}
