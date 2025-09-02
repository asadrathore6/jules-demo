package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class WeatherResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/weather")
          .then()
             .statusCode(200)
             .body(is("Weather App is running."));
    }

    @Test
    void testGetWeather() {
        given()
          .when().get("/api/weather")
          .then()
             .statusCode(200)
             .body("$", hasSize(3));
    }
}