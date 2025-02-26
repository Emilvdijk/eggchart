package com.example.eggchart.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class EggController {

  @GetMapping("/")
  String chart(){
    return "chart";
  }
}
