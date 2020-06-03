package it.matteoavanzini.example.trading.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.matteoavanzini.example.trading.model.Candle;
import it.matteoavanzini.example.trading.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    
}