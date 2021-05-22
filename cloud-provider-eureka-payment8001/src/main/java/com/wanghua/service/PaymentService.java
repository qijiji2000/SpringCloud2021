package com.wanghua.service;

import com.wanghua.entity.Payment;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
