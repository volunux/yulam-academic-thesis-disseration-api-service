package com.yulam.acalib.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StateExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StateExists {
  String message() default "State does not exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
