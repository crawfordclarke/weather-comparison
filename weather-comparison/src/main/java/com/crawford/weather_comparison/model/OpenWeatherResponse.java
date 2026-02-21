package com.crawford.weather_comparison.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherResponse(String name,
                                  Main main,
                                  List<Weather> weather,
                                  Wind wind
) {


    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Main(
            double temp,
            @JsonProperty("feels_like") double feelsLike,
            int humidity
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Weather(
            String description,
            String main
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Wind(
            double speed,
            double deg
    ) {}
}
