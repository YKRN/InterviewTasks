package com.example.websocket_demo.observer;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class ConcreteObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(ConcreteObserver.class);

    private final Queue<Double> prices;

    @Getter
    private final int windowSize = 5;

    public ConcreteObserver() {
        this.prices = new LinkedList<>();
    }

    @Override
    public void update(String message) {
        double price = Double.parseDouble(message);
        prices.add(price);
        if (prices.size() > windowSize) {
            prices.poll();
        }
        logger.info("Updated prices: {}", prices);
    }

    @Override
    public double calculateAverage() {
        double average = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        logger.info("Calculated moving average: {}", average);
        return average;
    }
}
