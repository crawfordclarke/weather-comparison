package com.crawford.weather_comparison.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherData(
        String city,
        double temperature,
        double feelsLike,
        String description,
        int humidity,
        double windSpeed) { }
