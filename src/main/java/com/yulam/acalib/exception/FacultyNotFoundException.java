package com.yulam.acalib.exception;

public class FacultyNotFoundException extends AcalibException {

  private static final String message = "Faculty does not exist";

  public FacultyNotFoundException(String message) {
    super(message);
  }

  public FacultyNotFoundException() {
    super(message);
  }
}
