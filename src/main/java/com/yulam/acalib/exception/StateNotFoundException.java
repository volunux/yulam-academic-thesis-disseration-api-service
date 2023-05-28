package com.yulam.acalib.exception;

public class StateNotFoundException extends AcalibException {

  private final String message = "State does not exists or cannot be found";

  public StateNotFoundException(String message) {
    super(message);
  }
}
