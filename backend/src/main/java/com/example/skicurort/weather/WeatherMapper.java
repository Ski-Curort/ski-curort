package com.example.skicurort.weather;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Slf4j
public class WeatherMapper {
    static Weather toWeather(OpenWeatherModel openWeather) {
        Weather weather = new Weather();

        weather.setCityName(openWeather.getName());
        weather.setTemp(openWeather.getWeatherDetails().getTemp());
        weather.setHumidity(openWeather.getWeatherDetails().getHumidity());
        weather.setPressure(openWeather.getWeatherDetails().getPressure());
        weather.setWindSpeed(openWeather.getWindDetails().getSpeed());
        weather.setWindDeg(openWeather.getWindDetails().getDeg());
        weather.setDateTime(LocalDateTime.ofEpochSecond(openWeather.getDateTime(), 0, ZoneOffset.UTC));
        log.info(String.valueOf(weather));
        return weather;
    }

}
// todo weatherMapper