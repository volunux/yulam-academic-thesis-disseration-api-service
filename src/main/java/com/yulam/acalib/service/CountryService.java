package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.dto.CountryDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface CountryService {

  Country getCountry(Integer id);

  List<Country> getCountries();

  Country saveCountry(CountryDto dto);

  Country updateCountry(Integer id, CountryDto dto);

  void deleteMany(DeleteIdsDto ids);

  void deleteAllCountry();

  boolean isCountryExists(Integer id);
}
