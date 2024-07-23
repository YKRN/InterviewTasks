package com.example.websocket_demo2.service;

import com.example.websocket_demo2.model.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class QuoteService {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final AtomicReference<Queue<Double>> prices = new AtomicReference<>(new LinkedList<>());

    @Value("${app.moving.average.window.size}")
    private int windowSize;

    private final MovingAverageCalculator movingAverageCalculator = MovingAverageCalculator.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    private void init() {
        System.out.println("Configured window size: " + windowSize);
    }

    public Quote processQuote(String jsonQuote) throws Exception {
        Quote quote = objectMapper.readValue(jsonQuote, Quote.class);
        executor.submit(() -> updatePrices(quote.getPrice()));
        double movingAverage = movingAverageCalculator.calculateMovingAverage(prices.get(), windowSize);
        quote.setMovingAverage(movingAverage);
        return quote;
    }

    private void updatePrices(double price) {
        Queue<Double> currentPrices = prices.get();
        if (currentPrices.size() == windowSize) {
            currentPrices.poll();
        }
        currentPrices.add(price);
        prices.set(currentPrices);
    }
}
