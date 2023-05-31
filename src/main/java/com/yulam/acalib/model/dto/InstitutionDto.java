package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.domain.Institution;
import com.yulam.acalib.model.domain.State;
import com.yulam.acalib.validator.CountryExists;
import com.yulam.acalib.validator.IsNumber;
import com.yulam.acalib.validator.StateExists;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDto {

  @NotBlank(message = "{institution.title.notEmpty}")
  @Size(min = 1, max = 200, message = "{institution.title.size}")
  private String title;

  @NotBlank(message = "{institution.country.notEmpty}")
  @IsNumber(message = "{institution.country.isNumber}")
  @CountryExists(message = "{institution.country.exists}")
  private String country;

  @NotBlank(message = "{institution.state.notEmpty}")
  @IsNumber(message = "{institution.state.isNumber}")
  @StateExists(message = "{institution.state.exists}")
  private String state;

  @NotBlank(message = "{institution.location.notEmpty}")
  @Size(min = 1, max = 200, message = "{institution.location.size}")
  private String location;

  @NotBlank(message = "{institution.foundingYear.notEmpty}")
  @IsNumber(message = "{institution.foundingYear.isNumber}")
  @Size(min = 4, max = 4, message = "{institution.foundingYear.size}")
  private String foundingYear;

  public Institution toInstitution() {
    return Institution.builder()
            .title(title)
            .location(location)
            .foundingYear(Integer.parseInt(foundingYear))
            .country(Country.builder()
                    .id(Integer.parseInt(country)).build())
            .state(State.builder()
                    .id(Integer.parseInt(state)).build())
            .build();
  }
}
