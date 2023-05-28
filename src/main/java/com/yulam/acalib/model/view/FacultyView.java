package com.yulam.acalib.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyView {

  private Integer id;
  private String title;
  private String code;
  private String description;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
