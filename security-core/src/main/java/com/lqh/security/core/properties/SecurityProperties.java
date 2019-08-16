package com.lqh.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 系统封装配置：如果配置了自己的登录页就走自己的登录页
 * 如果没有配置就走标准登录页
 *
 * @author liqihua5
 */
@Data
@Component
@ConfigurationProperties(prefix = "immoc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
}
