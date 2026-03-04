package com.crawford.weather_comparison.controller;


import com.crawford.weather_comparison.model.WeatherComparison;
import com.crawford.weather_comparison.model.WeatherData;
import com.crawford.weather_comparison.repository.WeatherRepository;
import com.crawford.weather_comparison.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;

    }

    @GetMapping("/city/{city}")
    public WeatherData getSingleCity(@PathVariable String city){
        return weatherService.getWeatherForCity(city);

    }

    @PostMapping("/compare")
    public WeatherComparison getCityComparison(@RequestBody List<String> cities){
        return weatherService.weatherComparison(cities);
    }


}
