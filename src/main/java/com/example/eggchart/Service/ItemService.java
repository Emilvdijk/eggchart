package com.example.eggchart.Service;

import com.example.eggchart.Entity.PriceNode;
import com.example.eggchart.Model.PriceResponse;
import com.example.eggchart.repository.ItemRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

  public PriceResponse callForEggPrice() {
    RestClient restClient = RestClient.builder().baseUrl(baseUrl).build();
    return restClient
        .get()
        .uri(uriBuilder -> uriBuilder.queryParam("id", eggId).build())
        .retrieve()
        .body(PriceResponse.class);
  }


  @Scheduled(cron = "0 */1 * * * *") // Every 1 minute
  public void scheduledNewPriceCheck(){
    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("attempting new price check {}",localDateTime);
    PriceNode priceNode = new PriceNode(callForEggPrice(), localDateTime);
    itemRepository.save(priceNode);
  }

    public List<PriceNode> getPriceList(){
      return itemRepository.findAll();
    }

//    PriceResponse(priceData=PriceResponse.PriceData(item1944=PriceResponse.ItemPrice(high=28, highTime=1740584725, low=28, lowTime=1740583456)))



}
