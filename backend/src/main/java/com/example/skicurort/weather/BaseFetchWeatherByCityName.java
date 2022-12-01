package com.example.skicurort.weather;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseFetchWeatherByCityName<T> {

  public final HttpClientWrapper httpClientWrapper = new HttpClientWrapper();
  protected final String cityName;

  public BaseFetchWeatherByCityName(String cityName) {
    this.cityName = cityName;
  }

  public final T execute() {
    T response = HttpClientWrapperProvider.getInstance().get(getUrl(), getClasz());
    log.info(String.valueOf(response));
    return response;
  }

  public abstract String getUrl();

  public abstract Class<T> getClasz();
}
