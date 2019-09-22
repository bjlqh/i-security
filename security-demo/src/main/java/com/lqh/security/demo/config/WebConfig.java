package com.lqh.security.demo.config;

import com.lqh.security.demo.filter.TimeFilter;
import com.lqh.security.demo.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: liqihua
 * Date: 2019/9/21 17:20
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 光使用 @Component还不够，还需要使用这个方法才能使拦截器生效
     * 拦截器不生效常见问题：
     * 1)是否有加@Configuration
     * 2)拦截路径是否有问题** 和 *
     * 3)拦截器最后路径一定要"/**"，如果是目录的话则是/*
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns("/user/*/**");
    }

    /**
     * 对于异步请求的拦截
     * //todo 打开拦截器看看，对于异步请求和同步请求的拦截的 异同
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
    }

    /**
     * FilterRegistrationBean
     * 声明一个过滤器 交给spring去管理
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registration.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/user/*");
        registration.setUrlPatterns(urls);
        return registration;
    }
}
