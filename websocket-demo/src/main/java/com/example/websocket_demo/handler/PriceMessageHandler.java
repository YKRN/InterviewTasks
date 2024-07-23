package com.example.websocket_demo.handler;

import com.example.websocket_demo.observer.ConcreteObserver;
import com.example.websocket_demo.singleton.WebSocketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

public class PriceMessageHandler implements MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(PriceMessageHandler.class);

    private final ConcreteObserver observer;

    public PriceMessageHandler() {
        this.observer = new ConcreteObserver();
    }

    @Override
    public void handle(String message) {
        logger.info("Received message: {}", message);
        observer.update(message);

        double average = observer.calculateAverage();
        logger.info("Calculated average: {}", average);

        try {
            WebSocketManager.getInstance().getSession().sendMessage(
                    new TextMessage("{\"average\": " + average + "}")
            );
            logger.info("Sent average: {}", average);
        } catch (IOException e) {
            logger.error("Error sending message", e);
        }
    }
}