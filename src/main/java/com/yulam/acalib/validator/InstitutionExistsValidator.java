package com.yulam.acalib.validator;

import com.yulam.acalib.service.InstitutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class InstitutionExistsValidator implements ConstraintValidator<InstitutionExists, String> {

  private final InstitutionService institutionService;
  public InstitutionExistsValidator(InstitutionService institutionService) {
    this.institutionService = institutionService;
  }

  @Override
  public void initialize(InstitutionExists institutionExists) {}

  @Override
  public boolean isValid(String id, ConstraintValidatorContext context) {
    try {
      return institutionService.isInstitutionExists(Integer.parseInt(id));
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }
  }
}
