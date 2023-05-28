package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.Department;
import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.validator.FacultyExists;
import com.yulam.acalib.validator.IsNumber;
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
  @IsNumber
  @FacultyExists
  private String faculty;

  public Department toDepartment() {
    return Department.builder()
            .title(title)
            .code(code)
            .description(code)
            .faculty(Faculty.builder()
                    .id(Integer.parseInt(faculty)).build())
            .build();
  }
}
