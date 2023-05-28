package com.yulam.acalib.controller;

import com.yulam.acalib.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  @ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Object handleUnsupportedMediaType() {
    return buildErrorMap("Content Type Not Supported");
  }

  @ExceptionHandler(value = { HttpMessageNotReadableException.class })
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Object handleUnreadableHttpBodyException() {
    return buildErrorMap("Invalid Request Body");
  }

  @ExceptionHandler(value = { Exception.class/*, ValidationException.class when a field is removed from the form */ })
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleInternalServerError(Exception ex, Model model) {
    System.out.println("Error details" + ex.getMessage() + " " + ex.getClass());
    model.addAttribute("errorMsg", ex.getMessage());
    model.addAttribute("timestamp", LocalDateTime.now());

    return "error/index";
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, Object> handleDataValidationError(MethodArgumentNotValidException ex) {
    Map<String, Object> errors = new HashMap<String, Object>();
    List<Map<String, Object>> errorValues = new ArrayList<>();

    ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
      Map<String, Object> errorMap = new HashMap<>();
      errorMap.put("field_name", fieldError.getField());

      List<String> errorMessages = ex
              .getFieldErrors(fieldError.getField())
              .stream()
              .map(DefaultMessageSourceResolvable::getDefaultMessage)
              .collect(Collectors.toList());

      errorMap.put("errors", errorMessages);
      errorValues.add(errorMap);
    });

    errors.put("message", "Invalid Request Body");
    errors.put("timestamp", LocalDateTime.now().toString());
    errors.put("fields", errorValues);
    errors.put("errorType", "DataValidation");
    return errors;
  }

  private Map<String, Object> buildErrorMap(String message) {
    Map<String, Object> errors = new HashMap<String, Object>();
    errors.put("message", message);
    errors.put("status", HttpStatus.BAD_REQUEST.value());
    errors.put("timestamp", LocalDateTime.now().toString());
    return errors;
  }
}
