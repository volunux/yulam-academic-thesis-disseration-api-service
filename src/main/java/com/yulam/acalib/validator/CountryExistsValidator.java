package com.yulam.acalib.validator;

import com.yulam.acalib.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@Component
public class CountryExistsValidator implements ConstraintValidator<CountryExists, Integer> {

  private final CountryService countryService;
  public CountryExistsValidator(CountryService countryService) {
    this.countryService = countryService;
  }

  @Override
  public void initialize(CountryExists countryExists) {}

  @Override
  public boolean isValid(Integer id, ConstraintValidatorContext context) {
    try {
      return countryService.isCountryExists(id);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return false;
    }
  }
}
