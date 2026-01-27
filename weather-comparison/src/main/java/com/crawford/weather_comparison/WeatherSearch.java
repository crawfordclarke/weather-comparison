package com.crawford.weather_comparison;


import jakarta.persistence.*;

@Entity
@Table(name = "WeatherSearches")
public class WeatherSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
