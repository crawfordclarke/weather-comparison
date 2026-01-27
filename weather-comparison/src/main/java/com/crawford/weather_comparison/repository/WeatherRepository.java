package com.crawford.weather_comparison.repository;

import com.crawford.weather_comparison.dto.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepository extends JpaRepository<WeatherData,Long> {

}
