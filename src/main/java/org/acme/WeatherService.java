package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class WeatherService {

    public List<Weather> getWeatherForecast() {
        return Arrays.asList(
            new Weather("New York", 70, "Sunny"),
            new Weather("London", 60, "Cloudy"),
            new Weather("Tokyo", 80, "Rainy")
        );
    }
}
