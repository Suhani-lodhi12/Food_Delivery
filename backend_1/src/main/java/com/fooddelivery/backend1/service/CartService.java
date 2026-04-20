package com.fooddelivery.backend1.service;

import com.fooddelivery.backend1.dto.AddToCartRequest;
import com.fooddelivery.backend1.dto.CartResponse;
import com.fooddelivery.backend1.model.CartItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CartService {

    private static final BigDecimal DELIVERY_FEE = BigDecimal.valueOf(2);

    private final Map<String, Map<String, CartItem>> userCarts = new ConcurrentHashMap<>();

    public CartResponse addItem(AddToCartRequest request) {
        Map<String, CartItem> cart = userCarts.computeIfAbsent(request.getUserId(), ignored -> new ConcurrentHashMap<>());

        cart.compute(request.getItemId(), (itemId, existing) -> {
            if (existing == null) {
                return new CartItem(itemId, request.getName(), request.getPrice(), request.getQuantity());
            }
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            existing.setName(request.getName());
            existing.setPrice(request.getPrice());
            return existing;
        });

        return getCart(request.getUserId());
    }

    public CartResponse removeOne(String userId, String itemId) {
        Map<String, CartItem> cart = userCarts.get(userId);
        if (cart == null) {
            return emptyCart();
        }

        cart.computeIfPresent(itemId, (id, existing) -> {
            int updatedQty = existing.getQuantity() - 1;
            if (updatedQty <= 0) {
                return null;
            }
            existing.setQuantity(updatedQty);
            return existing;
        });

        return getCart(userId);
    }

    public CartResponse getCart(String userId) {
        Map<String, CartItem> cart = userCarts.get(userId);
        if (cart == null || cart.isEmpty()) {
            return emptyCart();
        }

        List<CartItem> items = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItem item : cart.values()) {
            CartItem copy = new CartItem(item.getItemId(), item.getName(), item.getPrice(), item.getQuantity());
            items.add(copy);
            subtotal = subtotal.add(copy.getLineTotal());
        }

        CartResponse response = new CartResponse();
        response.setItems(items);
        response.setSubtotal(subtotal);
        response.setDeliveryFee(subtotal.compareTo(BigDecimal.ZERO) > 0 ? DELIVERY_FEE : BigDecimal.ZERO);
        response.setTotal(response.getSubtotal().add(response.getDeliveryFee()));
        return response;
    }

    public void clearCart(String userId) {
        userCarts.remove(userId);
    }

    public boolean isCartEmpty(String userId) {
        Map<String, CartItem> cart = userCarts.get(userId);
        return cart == null || cart.isEmpty();
    }

    public List<CartItem> getCartItemsSnapshot(String userId) {
        Map<String, CartItem> cart = userCarts.get(userId);
        if (cart == null || cart.isEmpty()) {
            return List.of();
        }

        List<CartItem> snapshot = new ArrayList<>();
        for (CartItem item : cart.values()) {
            snapshot.add(new CartItem(item.getItemId(), item.getName(), item.getPrice(), item.getQuantity()));
        }
        return snapshot;
    }

    private CartResponse emptyCart() {
        CartResponse response = new CartResponse();
        response.setItems(List.of());
        response.setSubtotal(BigDecimal.ZERO);
        response.setDeliveryFee(BigDecimal.ZERO);
        response.setTotal(BigDecimal.ZERO);
        return response;
    }
}
