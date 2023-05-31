package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.Role;
import com.yulam.acalib.validator.IsNumber;
import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

  @NotBlank
  @Size(min = 1, max = 200)
  private String title;

  @NotBlank
  @Size(min = 3, max = 5)
  private String code;

  @NotNull
  @IsNumber
  @Positive
  @Digits(integer = 4, fraction = 0)
  private String description;

  public Role toRole() {
    return Role.builder()
            .title(title)
            .code(code)
            .description(description)
            .build();
  }
}
