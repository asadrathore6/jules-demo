package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class WeatherResource {

    @Inject
    WeatherService weatherService;

    @GET
    @Path("/weather")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Weather App is running.";
    }

    @GET
    @Path("/api/weather")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Weather> getWeather() {
        return weatherService.getWeatherForecast();
    }
}
