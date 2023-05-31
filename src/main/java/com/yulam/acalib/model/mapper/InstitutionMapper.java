package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.Institution;
import com.yulam.acalib.model.view.InstitutionView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InstitutionMapper {

  private InstitutionMapper() {

  }

  public static InstitutionView toInstitutionView(@NotNull Institution institution) {
    return InstitutionView.builder()
            .id(institution.getId())
            .title(institution.getTitle())
            .foundingYear(institution.getFoundingYear())
            .createdOn(institution.getCreatedOn())
            .updatedOn(institution.getUpdatedOn())
            .countryView(CountryMapper.toCountryView(institution.getCountry()))
            .stateView(StateMapper.toStateView(institution.getState()))
            .build();
  }

  public static List<InstitutionView> toInstitutionViews(List<Institution> institutions) {
    if (institutions != null && !institutions.isEmpty()) {
      return institutions
              .stream()
              .map(InstitutionMapper::toInstitutionView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}
