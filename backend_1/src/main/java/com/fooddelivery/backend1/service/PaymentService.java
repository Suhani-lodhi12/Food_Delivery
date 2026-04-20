package com.fooddelivery.backend1.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@Service
public class PaymentService {

    public String generateFakePaymentId() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.ENGLISH));
        return "PAY-" + time + "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
