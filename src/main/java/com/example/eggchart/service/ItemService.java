package com.example.eggchart.service;

import com.example.eggchart.Entity.PriceNode;
import com.example.eggchart.Model.PriceResponse;
import com.example.eggchart.repository.ItemRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

  @NonNull
  ItemRepository itemRepository;

  @Value("${myapp.baseUrl}")
  private String baseUrl;

  @Value("${myapp.eggId}")
  private String eggId;

  @Autowired
  private KafkaTemplate<String, Object> template;

  public PriceResponse callForEggPrice() {
    RestClient restClient = RestClient.builder().baseUrl(baseUrl).build();
    return restClient
        .get()
        .uri(uriBuilder -> uriBuilder.queryParam("id", eggId).build())
        .retrieve()
        .body(PriceResponse.class);
  }

  @Scheduled(cron = "0 */5 * * * *") // Every 5 minute
  @EventListener(ApplicationReadyEvent.class)
  public void doScheduledPriceCheck(){
    log.info("engaging new price check");
    template.send("eggTopicScheduledCheck",null);
  }

  @KafkaListener(topics = "eggTopicScheduledCheck", id = "eggChartPriceCheck")
  public void NewPriceCheck() {
    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("attempting new price check {}", localDateTime);
    PriceNode priceNode = new PriceNode(callForEggPrice(), localDateTime);
    template.send("eggTopicSaveNewNode", priceNode);
  }

  @KafkaListener(topics = "eggTopicSaveNewNode", id = "eggChartSaveNode")
  public void SaveNewPriceCheck(PriceNode priceNode) {
    itemRepository.save(priceNode);
    log.info("new price log saved!");
  }

  public List<PriceNode> getPriceList() {
    return itemRepository.findAll();
    }
}
