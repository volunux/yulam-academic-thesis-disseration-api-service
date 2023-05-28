package com.yulam.acalib.controller;

public abstract class ControllerConfig {

  protected abstract String getCreateViewTitle();

  protected abstract String getUpdateViewTitle();

  protected abstract String getEntriesPath();

  protected abstract String getBasePath();

  protected String getFormProcessedMessageKey() {
    return "form-processing-status";
  }
}
