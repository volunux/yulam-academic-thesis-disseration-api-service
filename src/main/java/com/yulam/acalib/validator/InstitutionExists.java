package com.yulam.acalib.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InstitutionExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InstitutionExists {
  String message() default "Institution does not exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
