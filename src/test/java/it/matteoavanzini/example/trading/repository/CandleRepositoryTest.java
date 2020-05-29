package it.matteoavanzini.example.trading.repository;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.example.trading.model.Candle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandleRepositoryTest {

    Date mayFirst;

    @Autowired
    CandleRepository candleRepository;

    @Before
    public void init() throws ParseException {
        mayFirst = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-01");
        Candle candle = new Candle();
        candle.setOpenTime(mayFirst);
        candleRepository.save(candle);
    }

    @Test
    public void testCandleRepository() {
        Candle candle = candleRepository.findByOpenTime(mayFirst);
        assertNotNull(candle);
    }
    
}