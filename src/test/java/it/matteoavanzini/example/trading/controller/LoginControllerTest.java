package it.matteoavanzini.example.trading.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import it.matteoavanzini.example.trading.TradingIntegrationsTests;

public class LoginControllerTest extends TradingIntegrationsTests {
    
    @Test
    public void obtainAccessToken() throws Exception {

        String jsonLogin = "{\"username\":\"admin\",\"password\":\"admin\"}";
        RequestBuilder request = post("/login")
                .header("Content-Type", "application/json")
                .content(jsonLogin);

        MvcResult result = mvc.perform(request)
                                .andExpect(status().isOk())
                                .andReturn();

        String content = result.getResponse().getContentAsString();
        ResponseEntity<String> response = mapFromJson(content, ResponseEntity.class);
        String token = response.getBody();
        assertTrue(token.matches("^[a-zA-Z0-9\\-_]+?\\.[a-zA-Z0-9\\-_]+?\\.([a-zA-Z0-9\\-_]+)?$"));
    }
}