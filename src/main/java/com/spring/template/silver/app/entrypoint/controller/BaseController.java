package com.spring.template.silver.app.entrypoint.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@AllArgsConstructor
@ApiIgnore
public class BaseController {

  @GetMapping({"/", ""})

  public String home() {
    return "redirect:/swagger-ui/index.html#/";
  }

}