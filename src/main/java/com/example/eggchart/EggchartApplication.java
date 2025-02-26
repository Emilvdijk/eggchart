package com.example.eggchart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
}
