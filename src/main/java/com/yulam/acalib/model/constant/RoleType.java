package com.yulam.acalib.model.constant;

import lombok.Getter;

@Getter
public enum RoleType {

  SUPER_ADMINISTRATOR("SuperAdministrator"),
  ADMINISTRATOR("Administrator"),
  MEMBER("Member");

  private final String value;

  RoleType(String value) {
        this.value = value;
    }
}
