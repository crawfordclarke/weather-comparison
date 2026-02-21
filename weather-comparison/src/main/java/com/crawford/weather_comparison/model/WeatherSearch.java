package com.crawford.weather_comparison.model;


import com.crawford.weather_comparison.repository.WeatherRepository;
import jakarta.persistence.*;

@Entity
@Table(name = "WeatherSearches")
public class WeatherSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
