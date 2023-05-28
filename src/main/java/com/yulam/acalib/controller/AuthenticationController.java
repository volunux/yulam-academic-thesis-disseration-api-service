package com.yulam.acalib.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "")
public class AuthenticationController {

  @GetMapping(value = "/sign-in")
  public void signIn() {

  }

  @PostMapping(value = "/sign-in")
  public void completeSignIn() {

  }

  @GetMapping(value = "sign-up")
  public void signUp() {

  }

  @PostMapping(value = "process-sign-up")
  public void completeSignUp() {

  }
}
