package com.yulam.acalib.controller;

import com.yulam.acalib.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class AcalibExceptionHandler {

  @ExceptionHandler(value = { AuthorNotFoundException.class, CountryNotFoundException.class,
                              DepartmentNotFoundException.class, GradeNotFoundException.class,
                              InstitutionNotFoundException.class, MaterialNotFoundException.class,
                              MemberNotFoundException.class, StateNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public String handleNotFound(Exception ex, Model model) {
    model.addAttribute("errorMsg", ex.getMessage());
    model.addAttribute("timestamp", LocalDateTime.now());

    return "error/not-found-404";
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleInternalServerError(Exception ex, Model model) {
    model.addAttribute("errorMsg", ex.getMessage());
    model.addAttribute("timestamp", LocalDateTime.now());

    return "error/index";
  }
}
