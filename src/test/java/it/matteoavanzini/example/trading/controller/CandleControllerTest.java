package it.matteoavanzini.example.trading.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import it.matteoavanzini.example.trading.TradingIntegrationsTests;
import it.matteoavanzini.example.trading.model.Candle;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class CandleControllerTest extends TradingIntegrationsTests {

    @Test
    public void testGetCandles() throws Exception {

        String inputJson = "{\"dateStart\":\"2020-03-01\",\"dateEnd':\"2020-04-01\"}";

        mvc.perform(get("/api/v1/candle/list")
                .header(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(31)))
            .andDo(print());
    }

    @Test
    public void testGetCandle() throws Exception {

        String inputJson = "{\"openTime\":\"2020-05-15\"}";

        RequestBuilder request = get("/api/v1/candle/get")
                                .header(MediaType.APPLICATION_JSON_VALUE)
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
        assertThat(candle.getSymbol(), matchesPattern("^\\w$"));
        assertThat(new SimpleDateFormat("yyyy-MM-dd").format(candle.getOpenTime()), matchesPattern("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"));
    }
}