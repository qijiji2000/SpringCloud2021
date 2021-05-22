package com.wanghua.service.impl;

import com.wanghua.entity.Payment;
import com.wanghua.repository.PaymentRepository;
import com.wanghua.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentRepository paymentRepository;

    @Override
    public int create(Payment payment) {
        return paymentRepository.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.getPaymentById(id);
    }
}
