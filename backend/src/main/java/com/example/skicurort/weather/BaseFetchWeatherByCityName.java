package com.example.skicurort.weather;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseFetchWeatherByCityName<T> {

    public final HttpClientWrapper httpClientWrapper = new HttpClientWrapper();
    protected final String cityName;

    public BaseFetchWeatherByCityName(String cityName) {
        this.cityName = cityName;
    }
    public final T execute(){
        return HttpClientWrapperProvider.getInstance().get(getUrl(),getClasz());
    }
    public abstract String getUrl();
    public abstract Class<T> getClasz();
}
