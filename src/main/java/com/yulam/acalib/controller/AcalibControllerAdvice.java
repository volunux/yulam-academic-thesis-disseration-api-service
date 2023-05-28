package com.yulam.acalib.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice(basePackages = {"com.yulam.acalib"})
public class AcalibControllerAdvice {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(false);
    binder.registerCustomEditor(String.class, stringTrimmer);
  }

  @ModelAttribute()
  public void addAttributes(Model model, HttpServletRequest request) {
    model.addAttribute("requestUri", request.getServletPath());
    model.addAttribute("siteName", "Acalib");
    model.addAttribute("currentYear", LocalDate.now().getYear());
  }
}
