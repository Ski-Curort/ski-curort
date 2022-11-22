package com.example.skicurort.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
@Slf4j
@RestController
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
        Weather weather = weatherService.fetchWeatherByCityName(cityName).orElseThrow(() -> new NoSuchElementException("city not exist"));
        log.info(String.valueOf(weather));
        return weather;
    }
}
