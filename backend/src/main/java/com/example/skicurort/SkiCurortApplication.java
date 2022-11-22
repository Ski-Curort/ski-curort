package com.example.skicurort;

import com.example.skicurort.weather.Weather;
import com.example.skicurort.weather.WeatherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SkiCurortApplication {

  public static void main(String[] args) {
    SpringApplication.run(SkiCurortApplication.class, args);
  }
}
