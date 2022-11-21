package com.example.skicurort.weather;

import org.springframework.stereotype.Component;

@Component
public class ApiConfig {
  private static ApiConfig config;
  private final String baseAPIUrl;
  private final String appId;

  public ApiConfig(String baseAPIUrl, String appId) {
    this.baseAPIUrl = baseAPIUrl;
    this.appId = appId;
  }

  String getByCityNameQuery(String cityName) {
    return String.format("%s?q=%s&appId=%s&units=metric", baseAPIUrl, cityName, appId);
  }

  static ApiConfig getInstance() {
    if (config == null) {
      config =
          new ApiConfig(
              "https://api.openweathermap.org/data/2.5/weather",
              "baa6ece140be0985d8bf8766fa381d1d");
    }
    return config;
  }
}
