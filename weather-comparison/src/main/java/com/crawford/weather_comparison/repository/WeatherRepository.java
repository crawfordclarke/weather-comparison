package com.crawford.weather_comparison.repository;

import com.crawford.weather_comparison.model.WeatherData;
import com.crawford.weather_comparison.model.WeatherSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface WeatherRepository extends JpaRepository<WeatherSearch,Long> {
    List<WeatherSearch> findAllByOrderBySearchDateDesc(Pageable pageable);

}
