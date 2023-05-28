package com.yulam.acalib.model.dto.department;

import com.yulam.acalib.model.domain.Department;
import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.validator.FacultyExists;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

  @NotNull
  @Size(min = 1, max = 200)
  private String title;

  @NotNull
  @Size(min = 3, max = 5)
  private String code;

  @Size(min = 10, max = 1000)
  private String description;

  @NotNull
  @FacultyExists
  private Integer faculty;

  public Department toDepartment() {
    return Department.builder()
            .title(title)
            .code(code)
            .description(code)
            .faculty(Faculty.builder()
                    .id(faculty).build())
            .build();
  }
}
