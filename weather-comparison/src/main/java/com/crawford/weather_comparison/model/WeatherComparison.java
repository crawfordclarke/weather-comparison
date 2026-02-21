package com.crawford.weather_comparison.model;

import java.util.List;

public record WeatherComparison(
        List<WeatherData> cities,
        WeatherData warmest,
        WeatherData coldest,
        String recommendation) {
}
