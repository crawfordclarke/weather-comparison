package com.crawford.weather_comparison.service;

import com.crawford.weather_comparison.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {
    @Value("${OPENWEATHER_API_KEY}:missing_key")
    private String apiKey;

    WeatherRepository weatherRepository;



    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }



}
