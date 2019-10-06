package com.lqh.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqh.security.core.properties.LoginType;
import com.lqh.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: liqihua
 * Date: 2019/10/5 2:44
 */
@Slf4j
@Configuration("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * springmvc启动时会为我们注册一个ObjectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @param request
     * @param response
     * @param authentication 封装认证信息：当前用户的所有权限，发起请求的ip,session是什么，用户信息（userDetails）
     *                       Authentication只是一个接口，根据登录的方式不同，它的实现类也是不一样的。
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");

        //如果配置发送json,就用json;否则就用父类的默认的页面跳转
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication.getPrincipal()));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
