package com.hnp.alfrescorestapi.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NodeTypeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NodeTypeConstraint {
    String message() default "Invalid node type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
