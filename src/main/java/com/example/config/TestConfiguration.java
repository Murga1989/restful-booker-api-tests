package com.example.config;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;

public class TestConfiguration {

    public static void configureRestAssured() {
        // Configure REST Assured to use Jackson for JSON serialization/deserialization
        RestAssured.config = RestAssured.config()
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                        .defaultObjectMapperType(ObjectMapperType.JACKSON_2));

        // Set default content type
        RestAssured.requestSpecification = RestAssured.given()
                .contentType("application/json")
                .accept("application/json");
    }

    public static class APIEndpoints {
        public static final String BASE_URL = "https://restful-booker.herokuapp.com";
        public static final String BOOKING = "/booking";
        public static final String AUTH = "/auth";
        public static final String PING = "/ping";
    }

    public static class TestData {
        public static final String DEFAULT_USERNAME = "admin";
        public static final String DEFAULT_PASSWORD = "password123";
    }
}