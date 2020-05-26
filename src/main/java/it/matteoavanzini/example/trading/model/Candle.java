package it.matteoavanzini.example.trading.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Candle {
    public static final long H24 = 60*60*24;

    private int volume;
    private double closePrice;
    private double openPrice;
    private double maximumPrice;
    private double minimumPrice;
    private String symbol;
    private Date openTime;
    private long duration;

    public Candle() {
        duration = Candle.H24;
    }
}