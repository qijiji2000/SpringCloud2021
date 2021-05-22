package com.wanghua.controller;

import com.wanghua.entity.CommonResult;
import com.wanghua.entity.Payment;
import com.wanghua.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value(value = "${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


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


    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + "/t" + instance.getHost() + "/t" + instance.getUri());
        }
        return discoveryClient;
    }


    @GetMapping("/lb")
    public String getPaymentLB() {
        return serverPort;
    }


    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


}
