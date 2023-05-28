package com.yulam.acalib.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FacultyExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FacultyExists {
  String message() default "Faculty does not exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
