package com.example.skicurort.security;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Data
@EnableAsync
@Configuration
@ConfigurationProperties(prefix = "app.security")
public class AppSecurityConfigProperties {
  private List<String> authorizedRedirectUris = new ArrayList<>();
  private String jwtSecret;
  private int jwtExpirationMilliseconds;
}
