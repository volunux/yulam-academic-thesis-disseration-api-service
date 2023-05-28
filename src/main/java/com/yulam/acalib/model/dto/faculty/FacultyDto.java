package com.yulam.acalib.model.dto.faculty;

import com.yulam.acalib.model.domain.Faculty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {

  @NotNull
  @Size(min = 1, max = 200)
  private String title;

  @NotNull
  @Size(min = 3, max = 5)
  private String code;

  @Size(min = 10, max = 1000)
  private String description;

  public Faculty toFaculty() {
    return Faculty.builder()
            .title(title)
            .code(code)
            .description(code)
            .build();
  }
}
