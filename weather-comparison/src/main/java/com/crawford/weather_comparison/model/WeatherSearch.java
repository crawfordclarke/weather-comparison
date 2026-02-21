package com.crawford.weather_comparison.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "weather_searches")  // Table name in database
public class WeatherSearch {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 500)
    private String cities;


    @Column(name = "search_date", nullable = false)
    private LocalDateTime searchDate;


    @Column(name = "warmest_city", length = 100)
    private String warmestCity;


    @Column(name = "warmest_temp")
    private Double warmestTemp;


    @Column(name = "coldest_city", length = 100)
    private String coldestCity;


    @Column(name = "coldest_temp")
    private Double coldestTemp;



    public WeatherSearch() {
        this.searchDate = LocalDateTime.now();
    }


    public WeatherSearch(String cities, String warmestCity, Double warmestTemp,
                         String coldestCity, Double coldestTemp) {
        this.cities = cities;
        this.searchDate = LocalDateTime.now();  // Set to now
        this.warmestCity = warmestCity;
        this.warmestTemp = warmestTemp;
        this.coldestCity = coldestCity;
        this.coldestTemp = coldestTemp;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }

    public String getWarmestCity() {
        return warmestCity;
    }

    public void setWarmestCity(String warmestCity) {
        this.warmestCity = warmestCity;
    }

    public Double getWarmestTemp() {
        return warmestTemp;
    }

    public void setWarmestTemp(Double warmestTemp) {
        this.warmestTemp = warmestTemp;
    }

    public String getColdestCity() {
        return coldestCity;
    }

    public void setColdestCity(String coldestCity) {
        this.coldestCity = coldestCity;
    }

    public Double getColdestTemp() {
        return coldestTemp;
    }

    public void setColdestTemp(Double coldestTemp) {
        this.coldestTemp = coldestTemp;
    }


    @Override
    public String toString() {
        return "WeatherSearch{" +
                "id=" + id +
                ", cities='" + cities + '\'' +
                ", searchDate=" + searchDate +
                ", warmestCity='" + warmestCity + '\'' +
                ", warmestTemp=" + warmestTemp +
                ", coldestCity='" + coldestCity + '\'' +
                ", coldestTemp=" + coldestTemp +
                '}';
    }
}