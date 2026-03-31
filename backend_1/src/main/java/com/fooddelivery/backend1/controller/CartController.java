package com.fooddelivery.backend1.controller;

import com.fooddelivery.backend1.dto.AddToCartRequest;
import com.fooddelivery.backend1.dto.ApiResponse;
import com.fooddelivery.backend1.dto.CartResponse;
import com.fooddelivery.backend1.dto.RemoveFromCartRequest;
import com.fooddelivery.backend1.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ApiResponse<CartResponse> addToCart(@Valid @RequestBody AddToCartRequest request) {
        return ApiResponse.ok("Item added to cart", cartService.addItem(request));
    }

    @PostMapping("/remove-one")
    public ApiResponse<CartResponse> removeOne(@Valid @RequestBody RemoveFromCartRequest request) {
        return ApiResponse.ok("Item quantity updated", cartService.removeOne(request.getUserId(), request.getItemId()));
    }

    @GetMapping("/{userId}")
    public ApiResponse<CartResponse> getCart(@PathVariable String userId) {
        return ApiResponse.ok("Cart fetched", cartService.getCart(userId));
    }

    @PostMapping("/clear/{userId}")
    public ApiResponse<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ApiResponse.ok("Cart cleared", null);
    }
}
