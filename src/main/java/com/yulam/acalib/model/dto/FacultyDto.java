package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.Faculty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {

  @NotBlank(message = "{faculty.title.notEmpty}")
  @Size(min = 1, max = 200, message = "{faculty.title.size}")
  private String title;

  @NotBlank(message = "{faculty.code.notEmpty}")
  @Size(min = 3, max = 5, message = "{faculty.code.size}")
  private String code;

  public Faculty toFaculty() {
    return Faculty.builder()
            .title(title)
            .code(code)
            .build();
  }
}
