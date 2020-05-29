package it.matteoavanzini.example.trading.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.matteoavanzini.example.trading.TradingApplication;
import it.matteoavanzini.example.trading.TradingIntegrationsTests;
import it.matteoavanzini.example.trading.config.JwtTokenUtil;
import it.matteoavanzini.example.trading.model.Operation;
import it.matteoavanzini.example.trading.model.Order;
import it.matteoavanzini.example.trading.model.http.PutOrderRequest;
import it.matteoavanzini.example.trading.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradingApplication.class)
@WebAppConfiguration
public class OrderControllerTest extends TradingIntegrationsTests {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Before
    public void setUp() {
      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    public void testPutOrder() throws Exception {

        String token = jwtTokenUtil.generateToken(userService.loadUserByUsername("admin"));

        PutOrderRequest req = new PutOrderRequest();
        req.setSymbol("FRAUDOLENT");
        req.setOperation(Operation.SELL);
        req.setQuantity(10);

        MvcResult response = mvc.perform(put("/api/v1/order/add")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)
                .content(mapToJson(req)))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        Order order = mapFromJson(response.getResponse().getContentAsString(), Order.class);

        assertNotNull(order);
    }
}