package com.fooddelivery.backend1.controller;

import com.fooddelivery.backend1.dto.ApiResponse;
<<<<<<< HEAD
import com.fooddelivery.backend1.dto.OrderTrackingResponse;
=======
<<<<<<< HEAD
import com.fooddelivery.backend1.dto.OrderTrackingResponse;
=======
>>>>>>> 51974c875f20d5bb03c44be0f3765217cf54b45c
>>>>>>> 75c49a666787c79357325f140732950e0709225f
import com.fooddelivery.backend1.model.Order;
import com.fooddelivery.backend1.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 75c49a666787c79357325f140732950e0709225f
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
<<<<<<< HEAD
=======

=======

import java.util.List;

>>>>>>> 51974c875f20d5bb03c44be0f3765217cf54b45c
>>>>>>> 75c49a666787c79357325f140732950e0709225f
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 75c49a666787c79357325f140732950e0709225f
    @GetMapping("/{id}")
    public ApiResponse<OrderTrackingResponse> getOrderTrackingDetails(@PathVariable String id) {
        OrderTrackingResponse response = orderService.getOrderTrackingDetails(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order not found"));

        return ApiResponse.ok("Order tracking details fetched", response);
    }

    @GetMapping("/user/{userId}")
<<<<<<< HEAD
=======
=======
    @GetMapping("/{userId}")
>>>>>>> 51974c875f20d5bb03c44be0f3765217cf54b45c
>>>>>>> 75c49a666787c79357325f140732950e0709225f
    public ApiResponse<List<Order>> getOrders(@PathVariable String userId) {
        return ApiResponse.ok("Orders fetched", orderService.getOrders(userId));
    }
}
