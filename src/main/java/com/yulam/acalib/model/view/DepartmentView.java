package com.yulam.acalib.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentView {

  private Integer id;
  private String title;
  private String code;
  private FacultyView faculty;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;

}
