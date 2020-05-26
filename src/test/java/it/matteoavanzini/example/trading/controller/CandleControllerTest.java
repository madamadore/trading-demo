package it.matteoavanzini.example.trading.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import it.matteoavanzini.example.trading.TradingApplication;
import it.matteoavanzini.example.trading.TradingIntegrationsTests;
import it.matteoavanzini.example.trading.model.Candle;
import it.matteoavanzini.example.trading.model.http.ListCandleRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradingApplication.class)
@WebAppConfiguration
public class CandleControllerTest extends TradingIntegrationsTests {

    @Before
    public void init() {
        super.setUp();
    }
    
    @Test
    public void testGetCandles() throws Exception {

        Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-01");
        ListCandleRequest requestObject = new ListCandleRequest();
        requestObject.setSymbol("FRAUDOLENT");
        requestObject.setFromDate(fromDate);
        requestObject.setToDate(toDate);

        mvc.perform(get("/api/v1/candle/list")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(requestObject)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(31)));
    }

    @Test
    public void testGetCandle() throws Exception {

        String inputJson = "{\"symbol\":\"FRAUDOLENT\", \"openTime\":\"2020-05-15\"}";

        RequestBuilder request = get("/api/v1/candle/get")
                                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .content(inputJson);
        MvcResult result = mvc.perform(request)
                                .andExpect(status().isOk())
                                .andReturn();

        Candle candle = mapFromJson(result.getResponse().getContentAsString(), Candle.class);

        assertTrue(candle instanceof Candle);
        assertThat(Double.toString(candle.getClosePrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Double.toString(candle.getOpenPrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Double.toString(candle.getMaximumPrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Double.toString(candle.getMinimumPrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertThat(Long.toString(candle.getDuration()), matchesPattern("^[0-9]+$"));
        assertThat(candle.getSymbol(), matchesPattern("^\\S+$"));
        assertThat(new SimpleDateFormat("yyyy-MM-dd").format(candle.getOpenTime()), matchesPattern("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"));
    }
}