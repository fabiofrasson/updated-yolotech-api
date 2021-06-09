package com.yolotech.defapi;

import com.yolotech.defapi.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class YolotechDefApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(YolotechDefApiApplication.class, args);
  }
}
