package com.example.websocket_demo2.service;

import com.example.websocket_demo2.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuoteServiceTest {

    @Autowired
    private QuoteService quoteService;

    @MockBean
    private MovingAverageCalculator movingAverageCalculator;

    @BeforeEach
    void setUp() {
        // Initialization code if necessary
    }

    @Test
    void testProcessQuote() throws Exception {
        String jsonQuote = "{\"price\": 100.0}";
        Quote quote = quoteService.processQuote(jsonQuote);
        assertEquals(100.0, quote.getPrice());
        // Add assertions for the moving average if needed
    }
}
