package com.example.skicurort.weather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather,Long> {

}
