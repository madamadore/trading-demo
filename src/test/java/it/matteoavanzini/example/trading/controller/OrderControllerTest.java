package it.matteoavanzini.example.trading.controller;

import org.junit.Test;

import it.matteoavanzini.example.trading.TradingIntegrationsTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

@WithMockUser
public class OrderControllerTest extends TradingIntegrationsTests {
    
    @Test
    public void testPutOrderBuy() throws Exception {
        String inputJson = "{\"symbol\":\"DOLLAR\",\"operation\":\"BUY\",\"quantity\":2}";
        mvc.perform(get("/api/v1/order/add")
                .header(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void testPutOrderSell() throws Exception { 
        String inputJson = "{\"symbol\":\"DOLLAR\",\"operation\":\"SELL\",\"quantity\":2}";
        mvc.perform(get("/api/v1/order/add")
                .header(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void testGetOrderList() throws Exception {
        String inputJson = "{\"dateStart\":\"2020-03-01\",\"dateEnd\":\"2020-04-01\"}";
        mvc.perform(get("/api/v1/order/list")
                .header(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void testGetSingleOrder() throws Exception {
        String inputJson = "{\"id\":\"ABCDE\"}";
        mvc.perform(get("/api/v1/order/get")
                .header(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
            .andExpect(status().isOk())
            .andDo(print());
    }
}