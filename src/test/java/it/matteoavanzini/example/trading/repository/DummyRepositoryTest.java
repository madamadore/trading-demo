package it.matteoavanzini.example.trading.repository;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.example.trading.model.Candle;

@RunWith(SpringRunner.class)
public class DummyRepositoryTest {
    
    @Autowired
    DummyRepository dummyRepository;

    @Test
    public void testGetCandles() throws Exception {
        Date februaryFirst = new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2020");
        Date marchFirst = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2020");
        List<Candle> candles = dummyRepository.getCandles(februaryFirst, marchFirst);

        assertEquals(29, candles.size());
    }

    @Test
    public void testGetCandle() throws Exception {
        Date julyFirst = new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2020");
        Candle candle = dummyRepository.getCandle(julyFirst);

        assertTrue(candle instanceof Candle);
        assertThat(Double.toString(candle.getClosePrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Double.toString(candle.getOpenPrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Double.toString(candle.getMaximumPrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Double.toString(candle.getMinimumPrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Long.toString(candle.getDuration()), matchesPattern("^[0-9]+$"));
        assertThat(candle.getSymbol(), matchesPattern("^\\w$"));
        assertThat(new SimpleDateFormat("yyyy-MM-dd").format(candle.getOpenTime()), matchesPattern("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"));
    }
}