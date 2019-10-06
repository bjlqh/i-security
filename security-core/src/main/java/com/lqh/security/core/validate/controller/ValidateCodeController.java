package com.lqh.security.core.validate.controller;

import com.lqh.security.core.utils.RandomValidateCode;
import com.lqh.security.core.validate.code.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: liqihua
 * Date: 2019/10/6 15:22
 */

@Slf4j
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private RandomValidateCode randomValidateCode;


    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.生成验证码
        ImageCode imageCode = randomValidateCode.getRandCode(request);
        //2.将图片验证码放入session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        //3.写入响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());

    }
}
