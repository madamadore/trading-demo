package it.matteoavanzini.example.trading.service;

import org.springframework.security.core.userdetails.UserDetails;

import it.matteoavanzini.example.trading.model.Operation;
import it.matteoavanzini.example.trading.model.Order;

public interface OrderService {
    Order createOrder(UserDetails user, String symbol, Operation op, int quantity);
}