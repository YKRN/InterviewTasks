package com.example.websocket_demo.factory;

import com.example.websocket_demo.handler.MessageHandler;
import com.example.websocket_demo.handler.PriceMessageHandler;
import org.springframework.stereotype.Component;

@Component
public class ConcreteHandlerFactory implements HandlerFactory {

    @Override
    public MessageHandler getHandler(String type) {
        if ("price".equals(type)) {
            return new PriceMessageHandler();
        }
        return null;
    }
}
