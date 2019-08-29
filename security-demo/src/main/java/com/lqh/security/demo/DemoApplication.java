package com.lqh.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.lqh.security.core","com.lqh.security.browser"})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        String[] profiles = context.getEnvironment().getDefaultProfiles();
        for (String profile : profiles) {
            System.out.println("当前profile为：" + profile);
        }
    }

}
