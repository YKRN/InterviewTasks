package com.example.websocket_demo.handler;

import com.example.websocket_demo.singleton.WebSocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class QuoteWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(QuoteWebSocketHandler.class);

    private final MessageHandler messageHandler;

    public QuoteWebSocketHandler() {
        this.messageHandler = new PriceMessageHandler();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        WebSocketManager.getInstance().setSession(session);
        logger.info("WebSocket connection established");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        logger.info("Received message: {}", message.getPayload());
        messageHandler.handle(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("WebSocket connection closed: {}", status);
    }
}
