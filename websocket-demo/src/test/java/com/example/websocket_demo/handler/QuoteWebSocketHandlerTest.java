package com.example.websocket_demo.handler;

import com.example.websocket_demo.config.WebSocketConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


import java.io.IOException;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = WebSocketConfig.class)
public class QuoteWebSocketHandlerTest {

    @Autowired
    private QuoteWebSocketHandler handler;

    @Test
    void testHandleTextMessage() throws IOException {
        WebSocketSession session = mock(WebSocketSession.class);
        when(session.isOpen()).thenReturn(true);

        handler.afterConnectionEstablished(session);

        handler.handleTextMessage(session, new TextMessage("100.0"));

        verify(session, times(1)).sendMessage(any(TextMessage.class));
    }
}
