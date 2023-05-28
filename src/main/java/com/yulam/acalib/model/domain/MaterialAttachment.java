package com.yulam.acalib.model.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAttachment {

  private Integer id;
  private Material material;
}
