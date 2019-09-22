package com.lqh.security.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: liqihua
 * Date: 2019/9/22 12:44
 */
@Slf4j
@Component
public class MockQueue {

    private static final ExecutorService POOL = Executors.newFixedThreadPool(5);

    private String placeOrder;

    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {

        POOL.execute(() -> {
            log.info("接到下单请求," + placeOrder + ",开始处理...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求处理完毕，" + placeOrder);
        });
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
