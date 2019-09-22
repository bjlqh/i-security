package com.lqh.security.demo.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: liqihua
 * Date: 2019/9/21 15:00
 */
@Constraint(validatedBy = MyConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MyConstraint {

    //这三个属性必须都要有

    String message() default "{org.hibernate.validator.constraints.NotBlank.message}";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};
}
