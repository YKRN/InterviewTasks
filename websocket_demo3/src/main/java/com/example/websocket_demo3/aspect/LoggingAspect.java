package com.example.websocket_demo3.aspect;

import com.example.websocket_demo3.annotation.Yavuz;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @AfterReturning(value = "@annotation(yavuz)", returning = "result")
    public void logAfter(JoinPoint joinPoint, Yavuz yavuz, Object result) {
        System.out.println("Method " + joinPoint.getSignature().getName() + " executed. Result: " + result);
    }
}
