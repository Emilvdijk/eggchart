package com.example.eggchart;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class EggchartApplication {

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
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name("eggChart").partitions(10).replicas(1).build();
  }
}
