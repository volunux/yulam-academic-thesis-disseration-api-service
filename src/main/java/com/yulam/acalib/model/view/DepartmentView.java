package com.yulam.acalib.model.view;

import com.yulam.acalib.model.view.FacultyView;
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
  private String description;
  private FacultyView faculty;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;

}
