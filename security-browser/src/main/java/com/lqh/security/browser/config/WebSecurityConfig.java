package com.lqh.security.browser.config;

import com.lqh.security.core.properties.SecurityProperties;
import com.lqh.security.core.validate.filter.ValidateCodeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()//表单提交
                .loginPage("/authentication/require")//使用自定义登录页
                .loginProcessingUrl("/authentication/form")//使用自定义表单登录
                .successHandler(imoocAuthenticationSuccessHandler)//自定义登录成功处理器
                .failureHandler(imoocAuthenticationFailureHandler)//自定义登录失败处理器
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(), "/code/image")
                .permitAll()//过滤掉的请求
                .anyRequest()//所有请求
                .authenticated()//需要鉴权
                .and()
                .csrf().disable()//关闭跨域请求伪造
        ;
    }
}