package com.lqh.security.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Author: liqihua
 * Date: 2019/9/21 19:10
 * <p>
 * 在哪些方法上起作用
 * 在什么时候起作用 @Before @After @AfterThrowing @AfterThrowing @around
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.lqh.security.demo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");
        long start = System.currentTimeMillis();
        //方法参数
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg: " + arg);
        }
        Object object = pjp.proceed();

        System.out.println("time aspect end");
        long end = System.currentTimeMillis();
        System.out.println("time aspect finish 耗时: " + (end - start));

        return object;
    }
}
