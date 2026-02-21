package com.crawford.weather_comparison.mapper;

import com.crawford.weather_comparison.model.OpenWeatherResponse;
import com.crawford.weather_comparison.model.WeatherData;

public class WeatherMapper {
    public static WeatherData toWeatherData(OpenWeatherResponse response){
        if(response == null){
            throw new IllegalArgumentException("Open weather cannot be null");
        }
        String description = response.weather() != null && response.weather().isEmpty()
                ? response.weather().get(0).description()
                : "No description available";

        return new WeatherData(
                response.name(),
                response.main().temp(),
                response.main().feelsLike(),
                description,
                response.main().humidity(),
                response.wind().speed()
        );

        }

    private WeatherMapper(){
        throw new UnsupportedOperationException("Utility Class");
    }
}
