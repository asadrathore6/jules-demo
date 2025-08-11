package com.example.weatherapp.controller;

import com.example.weatherapp.AbstractIntegrationTest;
import com.example.weatherapp.entity.WeatherData;
import com.example.weatherapp.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class WeatherControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @BeforeEach
    void setUp() {
        weatherDataRepository.deleteAll();
    }

    @Test
    void shouldReturnWeatherData() throws Exception {
        // Arrange
        WeatherData data = new WeatherData();
        data.setCity("Test City");
        data.setTemperature(25.5);
        data.setDescription("Sunny");
        data.setTimestamp(LocalDateTime.now());
        weatherDataRepository.save(data);

        // Act & Assert
        mockMvc.perform(get("/api/weather"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city", is("Test City")))
                .andExpect(jsonPath("$[0].temperature", is(25.5)));
    }
}
