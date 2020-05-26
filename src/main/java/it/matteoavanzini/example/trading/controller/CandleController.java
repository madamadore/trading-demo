package it.matteoavanzini.example.trading.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.matteoavanzini.example.trading.model.Candle;
import it.matteoavanzini.example.trading.model.http.GetCandleRequest;
import it.matteoavanzini.example.trading.model.http.ListCandleRequest;
import it.matteoavanzini.example.trading.repository.DummyRepository;

@RestController
@RequestMapping("/api/v1/candle")
public class CandleController {
    
    @Autowired
    DummyRepository candleRepository;

    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Candle>> list(@RequestBody ListCandleRequest request) {
        String symbol = request.getSymbol();
        Date fromDateIncluded = request.getFromDate();
        Date toDateExcluded = request.getToDate();
        List<Candle> candles = candleRepository.getCandles(symbol, fromDateIncluded, toDateExcluded);
        return ResponseEntity.ok(candles);        
    }

    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Candle> list(@RequestBody GetCandleRequest request) {
        String symbol = request.getSymbol();
        Date openTime = request.getOpenTime();
        Candle candle = candleRepository.getCandle(symbol, openTime);
        return ResponseEntity.ok(candle);        
    }
}