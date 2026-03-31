package com.fooddelivery.backend1.dto;

import jakarta.validation.constraints.NotBlank;

public class RemoveFromCartRequest {

    @NotBlank(message = "userId is required")
    private String userId;

    @NotBlank(message = "itemId is required")
    private String itemId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
