package com.example.websocket_demo3.controller;

import com.example.websocket_demo3.model.Quote;
import com.example.websocket_demo3.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(com.example.websocket_demo3.controller.QuoteController.class)
public class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @BeforeEach
    public void setUp() {
        Quote quote = new Quote(5.0, 4.0);
        when(quoteService.processQuote(anyDouble())).thenReturn(quote);
    }

    @Test
    public void testAddPrice() throws Exception {
        String quoteJson = "{\"price\": 5.0}";

        mockMvc.perform(post("/api/quotes/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quoteJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"price\":5.0,\"movingAverage\":4.0}"));
    }

    @Test
    public void testInvalidAddPrice() throws Exception {
        String quoteJson = "{\"price\": \"invalid\"}";

        mockMvc.perform(post("/api/quotes/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quoteJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid price format."));
    }

    @Test
    public void testAddPriceWithMissingField() throws Exception {
        String quoteJson = "{}";

        mockMvc.perform(post("/api/quotes/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quoteJson))
                .andExpect(status().isBadRequest());
    }
}
