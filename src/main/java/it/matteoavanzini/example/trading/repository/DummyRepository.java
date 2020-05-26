package it.matteoavanzini.example.trading.repository;

import java.util.Date;
import java.util.List;

import it.matteoavanzini.example.trading.model.Candle;

public interface DummyRepository {
    List<Candle> getCandles(String symbol, Date fromDateIncluded, Date toDateExcluded);
    Candle getCandle(String symbol, Date openTime);
}