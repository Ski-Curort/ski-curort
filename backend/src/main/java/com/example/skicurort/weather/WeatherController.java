package com.example.skicurort.weather;

import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api")
public class WeatherController {
  WeatherService weatherService;

  @Autowired
  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/weather/{cityName}")
  public Weather showWeather(@PathVariable String cityName) {
    log.info(cityName);
    Weather weather =
        weatherService
            .fetchWeatherByCityName(cityName)
            .orElseThrow(() -> new NoSuchElementException("city not exist"));
    log.info(String.valueOf(weather));
    return weather;
  }
}
