package com.example.websocket_demo2.exception;

public class GlobalExceptionHandler {
      @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", System.currentTimeMillis());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request");
        errorResponse.put("message", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
  
}
