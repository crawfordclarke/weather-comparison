package com.crawford.weather_comparison.dto;

import java.util.List;

public record WeatherComparison(
        List<WeatherData> citties,
        WeatherData warmest,
        WeatherData coldest,
        String recommendation) {
}
