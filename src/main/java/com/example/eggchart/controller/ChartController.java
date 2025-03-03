package com.example.eggchart.controller;

import com.example.eggchart.Entity.PriceNode;
import com.example.eggchart.service.ItemService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ChartController {

  ItemService itemService;

  @GetMapping("/chart-data")
  public Map<String, Object> getChartData() {
    List<PriceNode> priceNodeList = itemService.getPriceList();
    List<String> xValues = new ArrayList<>();
    List<Integer> yValues = new ArrayList<>();
    priceNodeList.forEach(
        priceNode -> {
          xValues.add(priceNode.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
          yValues.add(priceNode.getGoldLow());
        });

    return Map.of(
        "xValues", xValues,
        "yValues", yValues);
  }
}
