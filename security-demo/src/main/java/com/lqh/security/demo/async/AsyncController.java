package com.lqh.security.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * Author: liqihua
 * Date: 2019/9/22 11:57
 */
@Slf4j
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order/sync")
    public String syncOrder() throws InterruptedException {
        log.info("主线程开始");
        Thread.sleep(2000);
        log.info("主线程返回");
        return "success";
    }

    @RequestMapping("/order/async")
    public Callable<String> asyncOrder() throws Exception {

        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("复线程开始");
                Thread.sleep(2000);
                log.info("复线程结束");
                return "success";
            }
        };
        log.info("主线程返回");
        return result;
    }

    @RequestMapping("/order")
    public DeferredResult<String> mockOrder() throws Exception {

        log.info("主线程开始");
        //生成订单号
        String orderId = RandomStringUtils.randomNumeric(8);
        //消息队列去处理
        mockQueue.setPlaceOrder(orderId);
        //
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderId, result);
        log.info("主线程返回");
        return result;
    }

}
