package com.lqh.security.core.properties;

public class BrowserProperties {
    /**
     * 用户么有配置loginPage那么给它一个默认值
     */
    private String loginPage = "/imooc-signIn.html";

    private LoginType loginType = LoginType.JSON;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
