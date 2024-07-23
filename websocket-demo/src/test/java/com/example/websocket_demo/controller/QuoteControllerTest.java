package com.example.websocket_demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuoteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetCurrentPrice() {

        String url = "http://localhost:" + port + "/api/quotes/current";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());


        assertEquals(Double.class, ((Map) response.getBody()).get("currentPrice").getClass());
    }

    @Test
    void testAddPrice() {

        String url = "http://localhost:" + port + "/api/quotes/price";
        Map<String, Double> quote = Map.of("price", 100.5);
        HttpEntity<Map<String, Double>> request = new HttpEntity<>(quote);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Price added.", response.getBody());
    }

    @Test
    void testInvalidAddPrice() {
        String url = "http://localhost:" + port + "/api/quotes/price";
        Map<String, String> invalidQuote = Map.of("price", "invalid");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(invalidQuote);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);


        System.out.println("Response Body: " + response.getBody());


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddPriceWithMissingField() {

        String url = "http://localhost:" + port + "/api/quotes/price";
        Map<String, Object> missingFieldQuote = Map.of();  // Empty map to simulate missing field
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(missingFieldQuote);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());


        assertEquals("Price field is required.", response.getBody());
    }
}
