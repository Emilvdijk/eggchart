package com.example.eggchart.Model;

import lombok.Data;

@Data
public class Item {

  private int id;
  private boolean members;
  private int lowAlch;
  private int limit;
  private int value;
  private int highAlch;
  private String icon;
  private String name;

}
