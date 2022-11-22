package com.example.skicurort.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class WeatherService {
    WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Optional<Weather> fetchWeatherByCityName(String cityName) {
        try {
            OpenWeatherModel openWeatherModel = new FetchWeatherByCity(cityName).execute();
            log.info(String.valueOf(openWeatherModel));
            return Optional.of(WeatherMapper.toWeather(openWeatherModel));
        } catch (NetworkApiException e) {

            return Optional.empty();
        }
    }
}
