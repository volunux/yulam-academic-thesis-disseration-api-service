package com.yulam.acalib.model.domain;

public enum MaterialType {

  THESIS("Thesis"),
  DISSERTATION("Dissertation"),
  JOURNAL_ARTICLE("Journal Article"),
  BOOK("Book"),
  CONFERENCE_PAPERS("Conference Paper"),
  LECTURE_NOTE("Lecture Note");

  private final String value;

  MaterialType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
