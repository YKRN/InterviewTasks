package com.example.websocket_demo2.controller;

import com.example.websocket_demo2.model.Quote;
import com.example.websocket_demo2.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @MessageMapping("/send")
    @SendTo("/topic/quotes")
    public Quote receiveQuote(String jsonQuote) throws Exception {
        return quoteService.processQuote(jsonQuote);
    }
}
