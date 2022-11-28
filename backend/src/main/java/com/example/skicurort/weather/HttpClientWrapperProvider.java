package com.example.skicurort.weather;

public class HttpClientWrapperProvider {
  private static HttpClientWrapper httpClientWrapper;

  public static HttpClientWrapper getInstance() {
    if (httpClientWrapper == null) {
      httpClientWrapper = new HttpClientWrapper();
    }
    return httpClientWrapper;
  }
}
