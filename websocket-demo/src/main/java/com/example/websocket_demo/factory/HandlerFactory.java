package com.example.websocket_demo.factory;

import com.example.websocket_demo.handler.MessageHandler;

public interface HandlerFactory {
    MessageHandler getHandler(String type);
}
