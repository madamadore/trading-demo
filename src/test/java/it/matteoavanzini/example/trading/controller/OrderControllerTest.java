package it.matteoavanzini.example.trading.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.matteoavanzini.example.trading.TradingApplication;
import it.matteoavanzini.example.trading.TradingIntegrationsTests;
import it.matteoavanzini.example.trading.config.JwtTokenUtil;
import it.matteoavanzini.example.trading.model.Operation;
import it.matteoavanzini.example.trading.model.Order;
import it.matteoavanzini.example.trading.model.http.ListOrderRequest;
import it.matteoavanzini.example.trading.model.http.PutOrderRequest;
import it.matteoavanzini.example.trading.repository.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        UserDetails admin = userService.loadUserByUsername("admin");

        for (int i=0; i<10; i++) {
            Order order = new Order();
            order.setSymbol("DOLLARO");
            order.setOperation(Operation.SELL);
            order.setUser(admin);
            order.setId(i);

            orderRepository.save(order);
        }
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

    @Test
    public void testGetOrder() throws Exception {
        String inputJson = "{\"id\":\"1\"}";
        String token = jwtTokenUtil.generateToken(userService.loadUserByUsername("admin"));

        RequestBuilder request = get("/api/v1/order/get")
                                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .header("Authorization", token)
                                .content(inputJson);
        MvcResult result = mvc.perform(request)
                                .andExpect(status().isOk())
                                .andReturn();

        Order order = mapFromJson(result.getResponse().getContentAsString(), Order.class);

        assertTrue(order instanceof Order);
        assertThat(Double.toString(order.getSinglePrice()), matchesPattern("^[0-9]+\\.?[0-9]*$"));
        assertEquals(order.getId(), 1L);
    }

    @Test
    public void testListOrder() throws Exception {
        Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-01");
        Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-01");
        
        String token = jwtTokenUtil.generateToken(userService.loadUserByUsername("admin"));

        ListOrderRequest requestObject = new ListOrderRequest();
        requestObject.setFromDate(fromDate);
        requestObject.setToDate(toDate);

        mvc.perform(get("/api/v1/order/list")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", token)
                .content(mapToJson(requestObject)))
            .andDo(print())
            .andExpect(status().isOk());
    }
}