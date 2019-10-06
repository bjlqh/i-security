package com.lqh.test;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: liqihua
 * Date: 2019/9/22 20:36
 */
public class CommonTest {

    public void test(String name, String age) {

    }

    @Test
    public void testParams() throws NoSuchMethodException {
        Method method = CommonTest.class.getMethod("test", String.class, String.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getName());
        }
    }

    @Test
    public void testLocalTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ISO_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("==========================");

        LocalDateTime time = LocalDateTime.of(2019, 10, 1, 12, 10, 8);
        System.out.println(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(time.format(formatter));

    }
}
