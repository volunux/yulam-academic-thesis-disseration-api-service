package com.yulam.acalib.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleView {

  private Integer id;
  private String title;
  private String code;
  private String description;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
