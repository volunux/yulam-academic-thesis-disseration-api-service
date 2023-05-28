package com.yulam.acalib.model.dto.country;

import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.validator.IsNumber;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

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
  private String foundingYear;

  @URL
  @Size(max = 500)
  private String mapLogoUrl;

  public Country toCountry() {
    return Country.builder()
            .title(title)
            .code(code)
            .foundingYear(Integer.parseInt(foundingYear))
            .mapLogoUrl(mapLogoUrl)
            .build();
  }
}
