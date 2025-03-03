package com.example.eggchart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EggController {

  @GetMapping("/")
  String chart() {
    return "chart";
  }
}
