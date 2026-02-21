package com.crawford.weather_comparison.service;

import com.crawford.weather_comparison.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String apiurl;
    private final String apikey;

    public WeatherService(RestTemplate restTemplate,
                          @Value("${OPENWEATHER_API_KEY}") String apikey,
                          @Value("${weather.api.url}") String apiurl){
        this.restTemplate = restTemplate;
        this.apikey = apikey;
        this.apiurl = apiurl;
    }












}
