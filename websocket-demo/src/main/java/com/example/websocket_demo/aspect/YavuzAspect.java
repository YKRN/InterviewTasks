package com.example.websocket_demo.aspect;

import com.example.websocket_demo.annotation.Yavuz;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class YavuzAspect {

    private static final Logger logger = LoggerFactory.getLogger(YavuzAspect.class);

    @Before("@annotation(yavuz)")
    public void logExecutionTime(Yavuz yavuz) {
        logger.info("Yavuz annotation is being processed");
    }
}