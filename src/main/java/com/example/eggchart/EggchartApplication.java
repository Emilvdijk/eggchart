package com.example.eggchart;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class EggchartApplication{

  public static void main(String[] args) {
    SpringApplication.run(EggchartApplication.class, args);
    log.info(
        """

            \033[1;34m\
              _    _      _ _         __          __        _     _ _
             | |  | |    | | |        \\ \\        / /       | |   | | |
             | |__| | ___| | | ___     \\ \\  /\\  / /__  _ __| | __| | |
             |  __  |/ _ \\ | |/ _ \\     \\ \\/  \\/ / _ \\| '__| |/ _` | |
             | |  | |  __/ | | (_) |     \\  /\\  / (_) | |  | | (_| |_|
             |_|  |_|\\___|_|_|\\___( )     \\/  \\/ \\___/|_|  |_|\\__,_(_)
                                  |/
            \033[0m""");

  }

  @Bean
  public KafkaAdmin admin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name("eggChart")
        .partitions(1)
        .replicas(1)
        .build();
  }
  
}
