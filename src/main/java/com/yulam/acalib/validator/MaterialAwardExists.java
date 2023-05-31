package com.yulam.acalib.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaterialAwardExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaterialAwardExists {
  String message() default "Material Award does not exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
