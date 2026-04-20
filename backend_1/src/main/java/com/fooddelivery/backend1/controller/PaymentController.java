package com.fooddelivery.backend1.controller;

import com.fooddelivery.backend1.dto.ApiResponse;
import com.fooddelivery.backend1.dto.CartResponse;
import com.fooddelivery.backend1.dto.PaymentResultResponse;
import com.fooddelivery.backend1.dto.ProceedPaymentRequest;
import com.fooddelivery.backend1.model.CartItem;
import com.fooddelivery.backend1.model.Order;
import com.fooddelivery.backend1.service.CartService;
import com.fooddelivery.backend1.service.OrderService;
import com.fooddelivery.backend1.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final CartService cartService;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public PaymentController(CartService cartService, PaymentService paymentService, OrderService orderService) {
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @PostMapping("/proceed")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PaymentResultResponse> proceedPayment(@Valid @RequestBody ProceedPaymentRequest request) {

        if (cartService.isCartEmpty(request.getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty. Add items before payment.");
        }

        CartResponse cart = cartService.getCart(request.getUserId());
        List<CartItem> itemsSnapshot = cartService.getCartItemsSnapshot(request.getUserId());

        String paymentId = paymentService.generateFakePaymentId();

        Order placedOrder = orderService.createPlacedOrder(
                request.getUserId(),
                itemsSnapshot,
                request.getDeliveryAddress(),
                request.getPaymentMethod(),
                paymentId,
                cart.getSubtotal(),
                cart.getDeliveryFee(),
                cart.getTotal()
        );

        cartService.clearCart(request.getUserId());

        PaymentResultResponse response = new PaymentResultResponse();
        response.setPaymentId(paymentId);
        response.setPaymentStatus("SUCCESS");
        response.setOrder(placedOrder);

        return ApiResponse.ok("Payment successful. Order placed and cart cleared.", response);
    }
}
