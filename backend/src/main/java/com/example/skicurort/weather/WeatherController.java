package com.example.skicurort.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api")
public class WeatherController {
    WeatherService weatherService;
@Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping("/weather/{cityName}")
    public Weather showWeather(@RequestBody Weather weather, @RequestParam String cityName){
        return weatherService.fetchWeatherByCityName(cityName).orElseThrow(()->new NoSuchElementException("city not exist"));
    }
}
