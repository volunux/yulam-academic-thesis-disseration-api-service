package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.Department;
import com.yulam.acalib.model.view.DepartmentView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {

  private DepartmentMapper() {

  }

  public static DepartmentView toDepartmentView(@NotNull Department department) {
    return DepartmentView.builder()
            .id(department.getId())
            .title(department.getTitle())
            .code(department.getCode())
            .description(department.getDescription())
            .createdOn(department.getCreatedOn())
            .updatedOn(department.getUpdatedOn())
            .faculty(FacultyMapper.toFacultyView(department.getFaculty()))
            .build();
  }

  public static List<DepartmentView> toDepartmentViews(List<Department> departments) {
    if (departments != null && !departments.isEmpty()) {
      return departments
              .stream()
              .map(DepartmentMapper::toDepartmentView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}
