package com.lqh.security.core.validate.filter;

/**
 * Author: liqihua
 * Date: 2019/10/6 16:06
 */

import com.lqh.security.core.properties.SecurityProperties;
import com.lqh.security.core.validate.code.ImageCode;
import com.lqh.security.core.validate.controller.ValidateCodeController;
import com.lqh.security.core.validate.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 在UsernamePasswordAuthenticationFilter过滤器之前拦截请求，去校验验证码
 */

@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private PathMatcher pathMatcher = new AntPathMatcher();

    private SecurityProperties securityProperties;

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 初始化url的配置，配置必须拦截的url
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String urlStr = securityProperties.getValidateCode().getImageCode().getUrls();
        String[] urlArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlStr, ",");
        urls.addAll(Arrays.asList(urlArr));
        //登录的请求
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1.获取所有需要校验的url
        boolean action = false;
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        if (action) {

            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                log.error("验证码校验异常：{}", e.getMessage(), e);
                //如果在校验时出现校验异常，把异常信息返回给前端
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        //2.不是登录请求，或不是校验异常 直接交给后面的过滤器
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        HttpServletRequest request = servletWebRequest.getRequest();
        //1.从session中获取图片
        ImageCode codeInSession = ((ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY));
        //2.获取用户输入的验证码
        String imageCode = ServletRequestUtils.getStringParameter(request, "imageCode");

        if (StringUtils.isBlank(imageCode)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(imageCode, codeInSession.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
    }


    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }
}
