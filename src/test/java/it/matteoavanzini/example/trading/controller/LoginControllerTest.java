package it.matteoavanzini.example.trading.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import it.matteoavanzini.example.trading.TradingApplication;
import it.matteoavanzini.example.trading.TradingIntegrationsTests;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradingApplication.class)
@WebAppConfiguration
public class LoginControllerTest extends TradingIntegrationsTests {
    
    @Before
    public void init() {
        super.setUp();
    }
    
    @Test
    public void obtainAccessToken() throws Exception {

        String jsonLogin = "{\"username\":\"admin\",\"password\":\"admin\"}";
        RequestBuilder request = post("/login")
                .header("Content-Type", "application/json")
                .content(jsonLogin);

        MvcResult result = mvc.perform(request)
                                .andExpect(status().isOk())
                                .andReturn();

        String token = result.getResponse().getHeader("Authorization");
        assertTrue(token.matches("^[a-zA-Z0-9\\-_]+?\\.[a-zA-Z0-9\\-_]+?\\.([a-zA-Z0-9\\-_]+)?$"));
    }
}