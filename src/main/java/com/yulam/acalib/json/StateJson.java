package com.yulam.acalib.json;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StateJson {

  private Info info;

  @Getter
  @Setter
  public static class Info {
    private String officialName;
    private String capital;
    private LocalDate dateCreated;
  }
}
