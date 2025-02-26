package com.example.eggchart.Entity;

import com.example.eggchart.Model.PriceResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class PriceNode {
  @Id @GeneratedValue private Long id;
  private LocalDateTime date;
  private Integer goldHigh;
  private Integer goldLow;

  public PriceNode(PriceResponse priceResponse, LocalDateTime localDateTime) {
    date= localDateTime;
    goldHigh = priceResponse.getPriceData().getItem1944().getHigh();
    goldLow = priceResponse.getPriceData().getItem1944().getLow();
  }
}
