package com.yulam.acalib.validator;

import com.yulam.acalib.service.FacultyService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class FacultyExistsValidator implements ConstraintValidator<FacultyExists, Integer> {

  private final FacultyService facultyService;
  public FacultyExistsValidator(FacultyService facultyService) {
    this.facultyService = facultyService;
  }

  @Override
  public void initialize(FacultyExists facultyExists) {}

  @Override
  public boolean isValid(Integer facultyId, ConstraintValidatorContext context) {
    System.out.println("Does exists : " + facultyService.isFacultyExists(facultyId));
    return facultyService.isFacultyExists(facultyId);
  }
}
