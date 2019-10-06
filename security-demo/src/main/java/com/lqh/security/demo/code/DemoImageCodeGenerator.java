package com.lqh.security.demo.code;

import com.lqh.security.core.validate.code.ImageCode;
import com.lqh.security.core.validate.service.IValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: liqihua
 * Date: 2019/10/7 0:03
 */

@Slf4j
@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements IValidateCodeGenerator {

    @Override
    public ImageCode getRandCode(HttpServletRequest request) {
        log.info("更高级的图形验证码生成代码");
        return null;
    }
}
