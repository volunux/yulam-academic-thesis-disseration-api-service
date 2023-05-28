package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.model.view.faculty.FacultyView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyMapper {

  private FacultyMapper() {

  }

  public static FacultyView toFacultyView(@NotNull Faculty faculty) {
    return FacultyView.builder()
            .id(faculty.getId())
            .title(faculty.getTitle())
            .code(faculty.getCode())
            .description(faculty.getDescription())
            .createdOn(faculty.getCreatedOn())
            .updatedOn(faculty.getUpdatedOn())
            .build();
  }

  public static List<FacultyView> toFacultyViews(List<Faculty> faculties) {
    if (faculties != null && !faculties.isEmpty()) {
      return faculties
              .stream()
              .map(FacultyMapper::toFacultyView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}
