package it.matteoavanzini.example.trading.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.example.trading.TradingIntegrationsTests;

@RunWith(SpringRunner.class)
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
}