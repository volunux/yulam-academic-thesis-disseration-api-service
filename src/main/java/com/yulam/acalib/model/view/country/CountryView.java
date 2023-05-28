package com.yulam.acalib.model.view.country;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryView {

  private Integer id;
  private String title;
  private String code;
  private Integer foundingYear;
  private String mapLogoUrl;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
