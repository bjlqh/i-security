package com.lqh.security.demo.service.impl;

import com.lqh.security.demo.service.IHelloService;
import org.springframework.stereotype.Service;

/**
 * Author: liqihua
 * Date: 2019/9/21 15:09
 */
@Service
public class HelloServiceImpl implements IHelloService {

    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello " + name;
    }
}
