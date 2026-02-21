package com.crawford.weather_comparison.mapper;

import com.crawford.weather_comparison.model.OpenWeatherResponse;
import com.crawford.weather_comparison.model.WeatherData;

import java.util.Optional;

public class WeatherMapper {
    public static WeatherData toWeatherData(OpenWeatherResponse response){
        if(response == null){
            throw new IllegalArgumentException("Open weather cannot be null");
        }
        String description = Optional.ofNullable(response.weather()) // Wrap the list in an Optional
                .flatMap(list -> list.stream().findFirst())              // Get a Stream and take the first item
                .map(OpenWeatherResponse.Weather::description)                               // If item exists, get its description
                .orElse("No description available");                     // Fallback if anything above was null/empty

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
