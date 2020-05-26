package it.matteoavanzini.example.trading.repository;

import java.util.Date;
import java.util.List;

import it.matteoavanzini.example.trading.model.Candle;

public interface DummyRepository {
    List<Candle> getCandles(Date fromDateIncluded, Date toDateExcluded);
    Candle getCandle(Date openTime);
}