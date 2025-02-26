package com.example.eggchart.controller;

import com.example.eggchart.Entity.PriceNode;
import com.example.eggchart.Service.ItemService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.IntStream;
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
    // Sample Data
    //    List<LocalDateTime> sampleTimeDate = IntStream.range(0, 5).mapToObj(i ->
    // LocalDateTime.now().minusHours(4-i )).toList();
    //
    //    List<String> xValues =
    //        sampleTimeDate.stream().map(localDateTime -> localDateTime.format(
    //            DateTimeFormatter.ISO_LOCAL_DATE_TIME)).toList();
    //    List<Integer> yValues = List.of(10, 15, 8, 12, 18,1000);

    List<PriceNode> priceNodeList = itemService.getPriceList();
    List<String> xValues = new ArrayList<>();
    List<Integer> yValues = new ArrayList<>();
    priceNodeList.forEach(priceNode -> {
      xValues.add(priceNode.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
      yValues.add(priceNode.getGoldLow());
    });

    return Map.of(
        "xValues", xValues,
        "yValues", yValues
    );
  }
}