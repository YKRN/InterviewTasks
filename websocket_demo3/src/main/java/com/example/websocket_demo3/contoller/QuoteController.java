package com.example.websocket_demo3.controller;

import com.example.websocket_demo3.model.Quote;
import com.example.websocket_demo3.service.QuoteService;
import com.example.websocket_demo3.annotation.Yavuz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/quotes")
    @Yavuz
    public Quote receiveQuote(String jsonQuote) throws Exception {
        return quoteService.processQuote(Double.parseDouble(jsonQuote));
    }
}
