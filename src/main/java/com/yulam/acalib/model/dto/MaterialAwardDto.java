package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.MaterialAward;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAwardDto {

  @NotNull
  @Size(min = 1, max = 200)
  private String title;

  @NotNull
  @Size(min = 3, max = 5)
  private String code;

  public MaterialAward toMaterialAward() {
    return MaterialAward.builder()
            .title(title)
            .code(code)
            .build();
  }
}
