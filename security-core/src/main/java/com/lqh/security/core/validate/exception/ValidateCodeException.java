package com.lqh.security.core.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Author: liqihua
 * Date: 2019/10/6 16:22
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
