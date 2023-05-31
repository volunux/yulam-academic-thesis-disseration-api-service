package com.yulam.acalib.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAwardView {

  private Integer id;
  private String title;
  private String code;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
