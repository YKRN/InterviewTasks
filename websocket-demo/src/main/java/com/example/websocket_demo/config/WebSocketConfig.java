package com.example.websocket_demo.config;

import com.example.websocket_demo.handler.QuoteWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(quoteWebSocketHandler(), "/quotes").setAllowedOrigins("*");
    }

    @Bean
    public QuoteWebSocketHandler quoteWebSocketHandler() {
        return new QuoteWebSocketHandler();
    }
}
