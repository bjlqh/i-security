package com.lqh.security.core.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrowserProperties {
    /**
     * 用户么有配置loginPage那么给它一个默认值
     */
    private String loginPage = "/immoc-signIn.html";
}
