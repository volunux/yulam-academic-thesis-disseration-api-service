package com.yulam.acalib.validator;

import com.yulam.acalib.service.MaterialAwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class MaterialAwardExistsValidator implements ConstraintValidator<MaterialAwardExists, Integer> {

  private final MaterialAwardService materialAwardService;
  public MaterialAwardExistsValidator(MaterialAwardService materialAwardService) {
    this.materialAwardService = materialAwardService;
  }

  @Override
  public void initialize(MaterialAwardExists materialAwardExists) {}

  @Override
  public boolean isValid(Integer id, ConstraintValidatorContext context) {
    try {
      return materialAwardService.isMaterialAwardExists(id);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }
  }
}
