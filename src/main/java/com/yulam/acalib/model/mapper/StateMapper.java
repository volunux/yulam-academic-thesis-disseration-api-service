package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.State;
import com.yulam.acalib.model.view.StateView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StateMapper {

  private StateMapper() {

  }

  public static StateView toStateView(@NotNull State state) {
    return StateView.builder()
            .id(state.getId())
            .title(state.getTitle())
            .capital(state.getCapital())
            .foundingYear(state.getFoundingYear())
            .mapLogoUrl(state.getMapLogoUrl())
            .countryView(CountryMapper.toCountryView(state.getCountry()))
            .createdOn(state.getCreatedOn())
            .updatedOn(state.getUpdatedOn())
            .build();
  }

  public static List<StateView> toStateViews(List<State> states) {
    if (states != null && !states.isEmpty()) {
      return states
              .stream()
              .map(StateMapper::toStateView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}
