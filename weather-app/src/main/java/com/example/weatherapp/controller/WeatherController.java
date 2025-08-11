package com.example.weatherapp.controller;

import com.example.weatherapp.entity.WeatherData;
import com.example.weatherapp.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherDataRepository weatherDataRepository;

    @Autowired
    public WeatherController(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    @GetMapping("/weather")
    public List<WeatherData> getAllWeatherData() {
        return weatherDataRepository.findAll();
    }
}
