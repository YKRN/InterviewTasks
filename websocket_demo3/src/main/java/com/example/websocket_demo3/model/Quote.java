package com.example.websocket_demo3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private double price;
    private double movingAverage;
}
