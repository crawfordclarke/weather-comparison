package com.crawford.weather_comparison.dto;

public record WeatherData(
        String city,
        double temperature,
        double feelsLike,
        String description,
        int humidity,
        double windSpeed) { }
