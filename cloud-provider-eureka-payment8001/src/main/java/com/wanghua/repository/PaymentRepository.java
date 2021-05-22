package com.wanghua.repository;

import com.wanghua.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentRepository {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
