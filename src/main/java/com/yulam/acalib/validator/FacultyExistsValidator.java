package com.yulam.acalib.validator;

import com.yulam.acalib.service.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class FacultyExistsValidator implements ConstraintValidator<FacultyExists, String> {

  private final FacultyService facultyService;
  public FacultyExistsValidator(FacultyService facultyService) {
    this.facultyService = facultyService;
  }

  @Override
  public void initialize(FacultyExists facultyExists) {}

  @Override
  public boolean isValid(String id, ConstraintValidatorContext context) {
    try {
      return facultyService.isFacultyExists(Integer.parseInt(id));
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }
  }
}
