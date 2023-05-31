package com.yulam.acalib.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateView {

  private Integer id;
  private String title;
  private String capital;
  private Integer foundingYear;
  private String mapLogoUrl;
  private CountryView countryView;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
