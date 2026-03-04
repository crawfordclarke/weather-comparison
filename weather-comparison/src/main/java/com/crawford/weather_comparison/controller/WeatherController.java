package com.crawford.weather_comparison.controller;


import com.crawford.weather_comparison.model.WeatherData;
import com.crawford.weather_comparison.repository.WeatherRepository;
import com.crawford.weather_comparison.service.WeatherService;
import org.springframework.web.bind.annotation.*;

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


}
