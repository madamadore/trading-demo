package it.matteoavanzini.example.trading.service;

import it.matteoavanzini.example.trading.model.Order;

public interface OrderService {
    public enum Operation {
        SELL, BUY
    };
    
    Order createOrder(String symbol, Operation op, int quantity);
}