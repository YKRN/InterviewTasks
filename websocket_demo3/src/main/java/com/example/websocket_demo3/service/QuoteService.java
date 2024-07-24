package com.example.websocket_demo3.service;

import com.example.websocket_demo3.model.Quote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class QuoteService {

    private final Queue<Double> window = new LinkedList<>();
    private double sum = 0.0;

    @Value("${moving.window.size}")
    int windowSize;

    public synchronized Quote processQuote(double price) {
        if (window.size() == windowSize) {
            sum -= window.poll();
        }
        window.offer(price);
        sum += price;
        double movingAverage = sum / window.size();

        return new Quote(price, movingAverage);
    }
}
