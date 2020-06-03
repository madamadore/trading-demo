package it.matteoavanzini.example.trading.repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.matteoavanzini.example.trading.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ 'date' : { $gte: ?0 , $lt: ?1 } }")
    List<Order> findBetweenDates(Date from, Date to);
}