package com.fooddelivery.backend1.dto;

import com.fooddelivery.backend1.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse {

    private List<CartItem> items;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal total;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
