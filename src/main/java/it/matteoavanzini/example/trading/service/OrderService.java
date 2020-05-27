package it.matteoavanzini.example.trading.service;

import it.matteoavanzini.example.trading.model.Operation;
import it.matteoavanzini.example.trading.model.Order;

public interface OrderService {
    Order createOrder(String symbol, Operation op, int quantity);
}