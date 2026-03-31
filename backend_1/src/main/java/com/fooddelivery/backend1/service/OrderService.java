package com.fooddelivery.backend1.service;

import com.fooddelivery.backend1.model.CartItem;
import com.fooddelivery.backend1.model.DeliveryAddress;
import com.fooddelivery.backend1.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final Map<String, List<Order>> userOrders = new ConcurrentHashMap<>();

    public Order createPlacedOrder(String userId,
                                   List<CartItem> cartItems,
                                   DeliveryAddress address,
                                   String paymentMethod,
                                   String paymentId,
                                   BigDecimal subtotal,
                                   BigDecimal deliveryFee,
                                   BigDecimal total) {

        Order order = new Order();
        order.setOrderId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setUserId(userId);
        order.setItems(new ArrayList<>(cartItems));
        order.setDeliveryAddress(address);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentId(paymentId);
        order.setPaymentStatus("SUCCESS");
        order.setOrderStatus("PLACED");
        order.setSubtotal(subtotal);
        order.setDeliveryFee(deliveryFee);
        order.setTotal(total);
        order.setPlacedAt(LocalDateTime.now());

        userOrders.computeIfAbsent(userId, ignored -> new ArrayList<>()).add(0, order);
        return order;
    }

    public List<Order> getOrders(String userId) {
        return userOrders.getOrDefault(userId, List.of());
    }
}
