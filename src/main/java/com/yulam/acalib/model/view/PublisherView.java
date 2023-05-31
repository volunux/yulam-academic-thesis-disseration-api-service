package com.yulam.acalib.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherView {

  private Integer id;
  private String title;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;
}
