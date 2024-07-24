package com.example.websocket_demo3.service;

import java.util.Queue;

public class MovingAverageCalculator {

    private static final MovingAverageCalculator INSTANCE = new MovingAverageCalculator();

    private MovingAverageCalculator() {
    }

    public static MovingAverageCalculator getInstance() {
        return INSTANCE;
    }

    public double calculateMovingAverage(Queue<Double> prices, int windowSize) {
        if (prices.size() < windowSize) {
            return 0;
        }
        double sum = 0;
        for (Double price : prices) {
            sum += price;
        }
        return sum / windowSize;
    }
}
