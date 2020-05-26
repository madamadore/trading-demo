package it.matteoavanzini.example.trading.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import it.matteoavanzini.example.trading.model.Candle;

@Repository
public class DummyRepositoryImpl implements DummyRepository {

    @Override
    public List<Candle> getCandles(String symbol, Date fromDateIncluded, Date toDateExcluded) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDateIncluded);

        List<Candle> rCandles = new LinkedList<>();
        while (calendar.getTime().before(toDateExcluded)) {
            Candle candle = getCandle(symbol, calendar.getTime());
            rCandles.add(candle);    
            calendar.add(Calendar.DATE, 1);
        }
        return rCandles;
    }

    @Override
    public Candle getCandle(String symbol, Date openTime) {
        Candle candle = new Candle();
        Random random = new Random(System.currentTimeMillis());

        candle.setClosePrice(random.nextDouble() * 100);
        candle.setOpenPrice(random.nextDouble() * 100);
        candle.setMaximumPrice(random.nextDouble() * 100);
        candle.setMinimumPrice(random.nextDouble() * 100);
        candle.setVolume(random.nextInt() * 1000);
        candle.setSymbol(symbol);
        candle.setOpenTime(openTime);
        candle.setDuration(Candle.H24);

        return candle;
    }
    
}