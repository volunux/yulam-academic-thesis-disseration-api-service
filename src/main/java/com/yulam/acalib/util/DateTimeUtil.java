package com.yulam.acalib.util;

public class DateTimeUtil {

  public static Long getTimeInMillis(int seconds, int minutes, int hours, int days) {
    return (long) 1000 * seconds * minutes * hours * days;
  }
}
