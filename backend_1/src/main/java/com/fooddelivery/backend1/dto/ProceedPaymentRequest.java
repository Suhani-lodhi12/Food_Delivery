package com.fooddelivery.backend1.dto;

import com.fooddelivery.backend1.model.DeliveryAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProceedPaymentRequest {

    @NotBlank(message = "userId is required")
    private String userId;

    @NotBlank(message = "paymentMethod is required")
    private String paymentMethod;

    @Valid
    @NotNull(message = "deliveryAddress is required")
    private DeliveryAddress deliveryAddress;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
