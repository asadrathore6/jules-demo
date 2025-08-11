package com.example.weatherapp.service;

import com.example.weatherapp.AbstractIntegrationTest;
import com.example.weatherapp.entity.WeatherData;
import com.example.weatherapp.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherServiceTest extends AbstractIntegrationTest {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @BeforeEach
    void setUp() {
        weatherDataRepository.deleteAll();
    }

    @Test
    void shouldFetchAndSaveMockData() {
        // Act
        weatherService.fetchAndSaveWeatherData();

        // Assert
        List<WeatherData> allData = weatherDataRepository.findAll();
        assertThat(allData).hasSize(1);

        WeatherData savedData = allData.get(0);
        assertThat(savedData.getCity()).isEqualTo("London (Mock)");
        assertThat(savedData.getDescription()).isEqualTo("Clear sky (Mock)");
        assertThat(savedData.getTemperature()).isBetween(-5.0, 30.0);
        assertThat(savedData.getTimestamp()).isNotNull();
    }
}
