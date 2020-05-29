package it.matteoavanzini.example.trading.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.matteoavanzini.example.trading.model.Candle;

public interface CandleRepository extends MongoRepository<Candle, String> {
    @Query("{ 'openTime' : ?0 }")
    public Candle findByOpenTime(Date openTime);
}