package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.validator.IsNumber;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

  @NotBlank(message = "{country.title.notEmpty}")
  @Size(min = 1, max = 200, message = "{country.title.size}")
  private String title;

  @NotBlank(message = "{country.code.notEmpty}")
  @Size(min = 3, max = 5, message = "{country.code.size}")
  private String code;

  @NotNull(message = "{country.foundingYear.notEmpty}")
  @IsNumber(message = "{country.foundingYear.isNumber}")
  @Size(min = 4, max = 4, message = "{country.foundingYear.size}")
  private String foundingYear;

  @URL(message = "{country.mapLogoUrl.isUrl}")
  @Size(max = 500, message = "{country.mapLogoUrl.size}")
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
