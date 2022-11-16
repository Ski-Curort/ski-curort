package com.example.skicurort.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class WeatherService {
    WeatherRepository weatherRepository;
@Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Optional<Weather> fetchWeatherByCityName(String cityName){
        try{
            OpenWeatherModel openWeatherModel = new FetchWeatherByCity(cityName).execute();
            return Optional.of(WeatherDto.toWeather(openWeatherModel));
        }catch (NetworkApiException e){

            return Optional.empty();
        }
    }
}
