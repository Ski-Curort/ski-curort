package com.example.skicurort.weather;

public class ApiConfig {
  private static ApiConfig config;
  private final String baseAPIUrl;
  private final String appId;

  public ApiConfig() {
    this.baseAPIUrl = "https://api.openweathermap.org/data/2.5/weather";
    this.appId = "e97aa6d17c38062e8989e4b4059cc4a6";
  }

  String getByCityNameQuery(String cityName) {
    return String.format("%s?q=%s&appId=%s&units=metric", baseAPIUrl, cityName, appId);
  }

  static ApiConfig getInstance() {
    if (config == null) {
      config = new ApiConfig();
    }
    return config;
  }
}
