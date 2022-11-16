package com.example.skicurort.weather;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WeatherDto {
    static Weather toWeather(OpenWeatherModel openWeather) {
        Weather weather = new Weather();
        weather.setCityName(openWeather.getName());
        weather.setTemp(openWeather.getWeatherDetails().getTemp());
        weather.setHumidity(openWeather.getWeatherDetails().getHumidity());
        weather.setPressure(openWeather.getWeatherDetails().getPressure());
        weather.setWindSpeed(openWeather.getWindDetails().getSpeed());
        weather.setWindDeg(openWeather.getWindDetails().getDeg());
        weather.setDateTime(LocalDateTime.ofEpochSecond(openWeather.getDateTime(), 0, ZoneOffset.UTC));
        return weather;
    }

}
