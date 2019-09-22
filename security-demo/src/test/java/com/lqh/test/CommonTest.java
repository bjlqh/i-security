package com.lqh.test;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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
}
