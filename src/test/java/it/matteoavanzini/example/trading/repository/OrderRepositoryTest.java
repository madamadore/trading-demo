package it.matteoavanzini.example.trading.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.example.trading.model.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    Date mayFirst, mayLast, mayMiddle;

    @Autowired
    OrderRepository orderRepository;

    @Before
    public void init() throws ParseException {
        mayFirst = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-01");
        mayLast = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-31");
        mayMiddle = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-15");

        Order orderMayFirst = new Order();
        Order orderMayMiddle = new Order();
        Order orderMayLast = new Order();
        
        orderMayFirst.setDate(mayFirst);
        orderMayMiddle.setDate(mayMiddle);
        orderMayLast.setDate(mayLast);

        orderRepository.save(orderMayFirst);
        orderRepository.save(orderMayMiddle);
        orderRepository.save(orderMayLast);
    }

    @Test
    public void testOrderRepositoryBetweenDates() throws Exception {
        List<Order> orders = orderRepository.findBetweenDates(mayFirst, mayLast);
        assertNotNull(orders);
        assertEquals(2, orders.size());
    }
    
}