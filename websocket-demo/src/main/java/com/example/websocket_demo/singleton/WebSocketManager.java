package com.example.websocket_demo.singleton;

import org.springframework.web.socket.WebSocketSession;
import lombok.Getter;
import lombok.Setter;

public class WebSocketManager {

    private static WebSocketManager instance;

    @Getter
    @Setter
    private WebSocketSession session;

    private WebSocketManager() {}

    public static WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }
}