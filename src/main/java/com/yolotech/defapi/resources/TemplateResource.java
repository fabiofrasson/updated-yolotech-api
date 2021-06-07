package com.yolotech.defapi.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateResource {

  @GetMapping("login")
  public String getLoginView() {
    return "login";
  }

  @GetMapping("welcome")
  public String getCourses() {
    return "welcome";
  }
}
