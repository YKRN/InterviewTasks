package com.example.websocket_demo3.service;

import com.example.websocket_demo3.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuoteServiceTest {

    private QuoteService quoteService;

    @Value("${moving.window.size}")
    private int windowSize;

    @BeforeEach
    public void setUp() {
        quoteService = new QuoteService();
        quoteService.windowSize = windowSize; // Inject window size for testing
    }

    @Test
    public void testProcessQuote() {
        double[] prices = {1.0, 2.0, 3.0, 4.0, 5.0};
        for (double price : prices) {
            Quote quote = quoteService.processQuote(price);
            System.out.println(quote);
        }

        Quote quote = quoteService.processQuote(6.0);
        assertEquals(4.0, quote.getMovingAverage(), 0.001);
    }
}
