package com.lqh.security.demo.validator;

import com.lqh.security.demo.service.IHelloService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Author: liqihua
 * Date: 2019/9/21 15:04
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Resource
    private IHelloService helloService;

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p/>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p/>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        helloService.greeting("tom");
        System.out.println("value: " + value);
        return false;
    }
}
