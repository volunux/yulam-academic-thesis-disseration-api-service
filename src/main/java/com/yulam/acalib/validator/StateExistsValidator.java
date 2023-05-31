package com.yulam.acalib.validator;

import com.yulam.acalib.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class StateExistsValidator implements ConstraintValidator<StateExists, String> {

  private final StateService stateService;
  public StateExistsValidator(@Qualifier("stateServiceImpl") StateService stateService) {
    this.stateService = stateService;
  }

  @Override
  public void initialize(StateExists stateExists) {}

  @Override
  public boolean isValid(String id, ConstraintValidatorContext context) {
    try {
      return stateService.isStateExists(Integer.parseInt(id));
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }
  }
}
