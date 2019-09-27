package com.lqh.security.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Author: liqihua
 * Date: 2019/9/22 13:06
 */

@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final ExecutorService POOL = Executors.newSingleThreadExecutor();

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("监听器启动");
        //模拟监听代码
        POOL.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                        String orderId = mockQueue.getCompleteOrder();
                        log.info("返回订单处理结果,订单号为:" + orderId);
                        deferredResultHolder.getMap().get(orderId).setResult("place order success");
                        mockQueue.setCompleteOrder(null);
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }
}
