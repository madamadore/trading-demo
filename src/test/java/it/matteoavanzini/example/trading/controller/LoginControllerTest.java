package it.matteoavanzini.example.trading.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import it.matteoavanzini.example.trading.TradingIntegrationsTests;

public class LoginControllerTest extends TradingIntegrationsTests {
    
    @Test
    public void obtainAccessToken() throws Exception {
  
        mvc.perform(
                post("/login")
                    .header("Content-Type", "application/json")
                    .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(status().isOk())
                .andReturn();
    }
}