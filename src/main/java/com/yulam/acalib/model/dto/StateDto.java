package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.domain.State;
import com.yulam.acalib.validator.IsNumber;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateDto {

  @NotNull
  @Size(min = 1, max = 200)
  private String title;

  @NotNull
  private String country;

  @NotNull
  private String capital;

  @NotNull
  @IsNumber
  @Positive
  @Digits(integer = 4, fraction = 0)
  private String foundingYear;

  @URL
  @Size(max = 500)
  private String mapLogoUrl;

  public State toState() {
    return State.builder()
            .title(title)
            .capital(capital)
            .mapLogoUrl(mapLogoUrl)
            .country(Country.builder()
                    .id(Integer.parseInt(country)).build())
            .foundingYear(Integer.parseInt(foundingYear))
            .build();
  }
}
