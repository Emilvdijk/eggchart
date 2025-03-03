package com.example.eggchart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PriceResponse {

  @JsonProperty("data")
  private PriceData priceData;

  @Data
  public static class PriceData {
    @JsonProperty("1944")
    private ItemPrice item1944;
  }

  @Data
  public static class ItemPrice {
    private int high;
    private long highTime;
    private int low;
    private long lowTime;
  }
}
