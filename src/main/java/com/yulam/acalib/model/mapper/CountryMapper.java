package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.view.country.CountryView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CountryMapper {

  private CountryMapper() {

  }

  public static CountryView toCountryView(@NotNull Country country) {
    return CountryView.builder()
            .id(country.getId())
            .title(country.getTitle())
            .code(country.getCode())
            .foundingYear(country.getFoundingYear())
            .mapLogoUrl(country.getMapLogoUrl())
            .createdOn(country.getCreatedOn())
            .updatedOn(country.getUpdatedOn())
            .build();
  }

  public static List<CountryView> toCountryViews(List<Country> faculties) {
    if (faculties != null && !faculties.isEmpty()) {
      return faculties
              .stream()
              .map(CountryMapper::toCountryView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}
