package com.yulam.acalib.model.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

  private Integer id;
  private String title;
  private String description;
}
