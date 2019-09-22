package com.lqh.security.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Author: liqihua
 * Date: 2019/9/21 17:08
 */

@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        System.out.println("time filter finish 耗时: " + (end - start));
    }


    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
