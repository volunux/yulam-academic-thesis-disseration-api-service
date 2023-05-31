package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.MaterialAward;
import com.yulam.acalib.model.view.MaterialAwardView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialAwardMapper {

  private MaterialAwardMapper() {

  }

  public static MaterialAwardView toMaterialAwardView(@NotNull MaterialAward materialAward) {
    return MaterialAwardView.builder()
            .id(materialAward.getId())
            .title(materialAward.getTitle())
            .code(materialAward.getCode())
            .createdOn(materialAward.getCreatedOn())
            .updatedOn(materialAward.getUpdatedOn())
            .build();
  }

  public static List<MaterialAwardView> toMaterialAwardViews(List<MaterialAward> faculties) {
    if (faculties != null && !faculties.isEmpty()) {
      return faculties
              .stream()
              .map(MaterialAwardMapper::toMaterialAwardView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}
