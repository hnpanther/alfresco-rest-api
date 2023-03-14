package com.hnp.alfrescorestapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NodeTypeValidator implements ConstraintValidator<NodeTypeConstraint, String> {


    @Override
    public void initialize(NodeTypeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(s == null || s.isEmpty() || s.isBlank()) {
            return false;
        }
        return s.equals("cm:folder") || s.equals("cm:content");
    }
}
