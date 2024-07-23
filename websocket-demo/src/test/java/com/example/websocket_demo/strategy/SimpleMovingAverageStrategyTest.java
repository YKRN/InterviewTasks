package com.example.websocket_demo.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMovingAverageStrategyTest {

    private SimpleMovingAverageStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new SimpleMovingAverageStrategy(3);
    }

    @Test
    void testAddPriceAndCalculateAverage() {
        strategy.addPrice(100.0);
        assertEquals(100.0, strategy.calculateAverage());

        strategy.addPrice(200.0);
        assertEquals(150.0, strategy.calculateAverage());

        strategy.addPrice(300.0);
        assertEquals(200.0, strategy.calculateAverage());

        strategy.addPrice(400.0);
        assertEquals(300.0, strategy.calculateAverage());
    }
}
