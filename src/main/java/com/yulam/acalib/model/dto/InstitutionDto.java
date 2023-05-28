package com.yulam.acalib.model.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDto {

  @NotBlank
  private String title;

  @NotNull
  private Integer country;

  @NotNull
  private Integer state;

  @NotBlank
  private String location;

  @NotNull
  @Digits(integer = 4, fraction = 0)
  private Integer foundingYear;
}
