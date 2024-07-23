package com.example.websocket_demo.controller;

import com.example.websocket_demo.strategy.SimpleMovingAverageStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final SimpleMovingAverageStrategy movingAverageStrategy;

    public QuoteController(@Value("${websocket.window.size}") int windowSize) {
        this.movingAverageStrategy = new SimpleMovingAverageStrategy(windowSize);
    }

    private final Map<String, Double> currentPrices = new ConcurrentHashMap<>();

    @PostMapping("/price")
    public ResponseEntity<String> addPrice(@RequestBody Map<String, Object> request) {
        if (!request.containsKey("price")) {
            return new ResponseEntity<>("Price field is required.", HttpStatus.BAD_REQUEST);
        }

        Object priceObj = request.get("price");
        if (!(priceObj instanceof Number)) {
            return new ResponseEntity<>("Invalid price format.", HttpStatus.BAD_REQUEST);
        }

        // Process the price and add it to the system
        return new ResponseEntity<>("Price added.", HttpStatus.OK);
    }
    @GetMapping("/average")
    public double getMovingAverage() {
        return movingAverageStrategy.calculateAverage();
    }

    @GetMapping("/current")
    public Map<String, Double> getCurrentPrice() {
        return currentPrices;
    }
}