package com.lqh.security.core.validate.service;

import com.lqh.security.core.validate.code.ImageCode;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: liqihua
 * Date: 2019/10/6 23:49
 */
public interface IValidateCodeGenerator {

    ImageCode getRandCode(HttpServletRequest request);
}
