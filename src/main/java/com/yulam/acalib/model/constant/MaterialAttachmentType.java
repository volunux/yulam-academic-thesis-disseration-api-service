package com.yulam.acalib.model.constant;

import lombok.Getter;

@Getter
public enum MaterialAttachmentType {

  ABSTRACT("Abstract"),
  DOCUMENT("Document");

  private final String value;

  MaterialAttachmentType(String value) {
    this.value = value;
  }
}
