package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.Department;
import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.validator.FacultyExists;
import com.yulam.acalib.validator.IsNumber;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

  @NotBlank(message = "{department.title.notEmpty}")
  @Size(min = 1, max = 200, message = "{department.title.size}")
  private String title;

  @NotBlank(message = "{department.code.notEmpty}")
  @Size(min = 3, max = 5, message = "{department.code.size}")
  private String code;

  @NotBlank(message = "{department.faculty.notEmpty}")
  @IsNumber(message = "{department.faculty.isNumber}")
  @FacultyExists(message = "{department.faculty.exists}")
  private String faculty;

  public Department toDepartment() {
    return Department.builder()
            .title(title)
            .code(code)
            .faculty(Faculty.builder()
                    .id(Integer.parseInt(faculty)).build())
            .build();
  }
}
