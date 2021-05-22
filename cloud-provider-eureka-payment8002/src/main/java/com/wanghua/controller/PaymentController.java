package com.wanghua.controller;


import com.wanghua.entity.CommonResult;
import com.wanghua.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.wanghua.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value(value = "${server.port}")
    private String serverPort;


    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort=" + serverPort, result);
        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort=" + serverPort, payment);
        } else {
            return new CommonResult(444, "无记录，查询ID:" + id, null);
        }
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        return serverPort;
    }

}
