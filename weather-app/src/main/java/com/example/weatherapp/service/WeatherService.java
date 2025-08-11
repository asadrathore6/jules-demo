package com.example.weatherapp.service;

import com.example.weatherapp.entity.WeatherData;
import com.example.weatherapp.repository.WeatherDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherDataRepository weatherDataRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(WeatherDataRepository weatherDataRepository, RestTemplate restTemplate) {
        this.weatherDataRepository = weatherDataRepository;
        this.restTemplate = restTemplate;
    }

    // Schedule to run every minute for demonstration.
    @Scheduled(fixedRate = 60000)
    public void fetchAndSaveWeatherData() {
        log.info("Scheduler running: Fetching weather data...");

        // --- MOCK DATA IMPLEMENTATION ---
        // This section uses mock data because a real API requires a key.
        // The code is fully functional using this mock data.
        // Replace this block with the "REAL API CALL" block below once you have an API key.
        WeatherData mockData = new WeatherData();
        mockData.setCity("London (Mock)");
        // Generate random temperature between -5 and 30
        double randomTemp = -5 + (new Random().nextDouble() * 35);
        mockData.setTemperature(Math.round(randomTemp * 10.0) / 10.0); // Round to one decimal place
        mockData.setDescription("Clear sky (Mock)");
        mockData.setTimestamp(LocalDateTime.now());

        weatherDataRepository.save(mockData);
        log.info("Successfully saved MOCK weather data for {}", mockData.getCity());

        /*
        --- REAL API CALL (EXAMPLE) ---
        // To use this, you must get a free API key from a provider like WeatherAPI.com or OpenWeatherMap.
        // 1. Uncomment this block.
        // 2. Delete the "MOCK DATA IMPLEMENTATION" block above.
        // 3. Replace "YOUR_API_KEY_HERE" with your actual key.
        // 4. You may need to create DTO classes to match the JSON structure of your chosen API.

        final String apiKey = "YOUR_API_KEY_HERE";
        final String city = "London";
        // Example URL for OpenWeatherMap
        final String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            // The response from weather APIs is often nested. You'll need to map it to your WeatherData object.
            // For example, you might get a JSON response and need to extract temp from `main.temp`.
            // A common way to handle this is to fetch the response as a String or a Map/JsonNode, then parse it.

            // Example of manual parsing:
            // com.fasterxml.jackson.databind.JsonNode root = restTemplate.getForObject(apiUrl, com.fasterxml.jackson.databind.JsonNode.class);
            // if (root != null) {
            //     String cityName = root.path("name").asText();
            //     double temp = root.path("main").path("temp").asDouble();
            //     String desc = root.path("weather").get(0).path("description").asText();
            //
            //     WeatherData realData = new WeatherData();
            //     realData.setCity(cityName);
            //     realData.setTemperature(temp);
            //     realData.setDescription(desc);
            //     realData.setTimestamp(LocalDateTime.now());
            //
            //     weatherDataRepository.save(realData);
            //     log.info("Successfully fetched and saved REAL weather data for {}", realData.getCity());
            // }
        } catch (Exception e) {
            log.error("Error fetching real weather data: {}", e.getMessage());
        }
        */
    }
}
