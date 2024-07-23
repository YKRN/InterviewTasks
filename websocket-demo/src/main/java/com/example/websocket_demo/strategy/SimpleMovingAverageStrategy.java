package com.example.websocket_demo.strategy;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleMovingAverageStrategy implements AverageStrategy {

    private final Queue<Double> prices;
    private final int windowSize;

    public SimpleMovingAverageStrategy(int windowSize) {
        this.prices = new LinkedList<>();
        this.windowSize = windowSize;
    }

    public void addPrice(double price) {
        prices.add(price);
        if (prices.size() > windowSize) {
            prices.poll();
        }
    }

    @Override
    public double calculateAverage() {
        return prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}