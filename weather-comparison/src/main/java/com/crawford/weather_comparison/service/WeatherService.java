package com.crawford.weather_comparison.service;

import com.crawford.weather_comparison.mapper.WeatherMapper;
import com.crawford.weather_comparison.model.OpenWeatherResponse;
import com.crawford.weather_comparison.model.WeatherComparison;
import com.crawford.weather_comparison.model.WeatherData;
import com.crawford.weather_comparison.model.WeatherSearch;
import com.crawford.weather_comparison.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String apiurl;
    private final String apikey;
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository,RestTemplate restTemplate,
                          @Value("${weather.api.key}") String apikey,
                          @Value("${weather.api.url}") String apiurl){
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
        this.apikey = apikey;
        this.apiurl = apiurl;
    }

    public WeatherComparison weatherComparison(List<String> cities){
        if(cities == null || cities.isEmpty()){
            throw new IllegalArgumentException("Cities list cannot be empty");
        }

        List<WeatherData> weatherDataList = new ArrayList<>();

        for(String city : cities){
            try{
                WeatherData data = fetchWeatherData(city);
                weatherDataList.add(data);
            } catch (RestClientException e ){
                System.err.println("Erro fetching weather for " + city);
            }

            if(weatherDataList.isEmpty()){
                throw new RuntimeException("Could not fetch weather data for any city");
            }
        }

        WeatherData warmest = weatherDataList.stream()
                .max(Comparator.comparingDouble(WeatherData::temperature))
                .orElseThrow();

        WeatherData coldest = weatherDataList.stream()
                .min(Comparator.comparingDouble(WeatherData::temperature))
                .orElseThrow();

        String recommendation = generateRecommendation(warmest,coldest);

        saveSearch(cities,warmest,coldest);

        return new WeatherComparison(weatherDataList, warmest, coldest, recommendation);


    }
    private WeatherData fetchWeatherData(String city){
        String url = UriComponentsBuilder
                .fromUriString(apiurl)
                .queryParam("q", city)
                .queryParam("appid", apikey)
                .queryParam("units", "metric")
                .toUriString();


        OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);

        if (response == null) {
            throw new RuntimeException("No response from weather API for city: " + city);
        }

        // Transform to your domain model
        return WeatherMapper.toWeatherData(response);

    }

    private String generateRecommendation(WeatherData warmest, WeatherData coldest){
        double tempDiff = warmest.temperature() - coldest.temperature();

        if (tempDiff > 10) {
            return String.format("Significant temperature difference! %s (%s°C) is much warmer than %s (%s°C). " +
                            "Consider %s for warm weather activities.",
                    warmest.city(), warmest.temperature(),
                    coldest.city(), coldest.temperature(),
                    warmest.city());
        } else if (tempDiff > 5) {
            return String.format("%s (%s°C) is noticeably warmer than %s (%s°C).",
                    warmest.city(), warmest.temperature(),
                    coldest.city(), coldest.temperature());
        } else {
            return String.format("Temperature is similar across all cities (difference: %.1f°C). " +
                            "All cities have comparable weather conditions.",
                    tempDiff);
        }

    }

    private void saveSearch(List<String> cities, WeatherData warmest, WeatherData coldest){
        try{
            WeatherSearch search = new WeatherSearch(
                    String.join(",", cities),
                    warmest.city(),
                    warmest.temperature(),
                    coldest.city(),
                    coldest.temperature()
            );
            weatherRepository.save(search);
        } catch (Exception e) {
            System.err.println(("Error saving search") + e.getMessage());
        }
    }

    public List<WeatherSearch> getRecentSearches(int limit){
        return weatherRepository.findAll().stream()
                .sorted(Comparator.comparing(WeatherSearch::getSearchDate).reversed())
                .limit(limit)
                .toList();
    }

    public WeatherData getWeatherForCity(String city){
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City name cannot be blank");
        }
        return fetchWeatherData(city);
    }












}
