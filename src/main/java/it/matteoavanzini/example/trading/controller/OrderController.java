package it.matteoavanzini.example.trading.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.matteoavanzini.example.trading.model.Operation;
import it.matteoavanzini.example.trading.model.Order;
import it.matteoavanzini.example.trading.model.http.PutOrderRequest;
import it.matteoavanzini.example.trading.service.OrderService;

@RestController
@RequestMapping(value = "/api/v1/order", consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    OrderService orderService;
    
    @PutMapping("/add")
    public Order createOrder(@RequestBody PutOrderRequest request, Principal principal) {
        String symbol = request.getSymbol();
        Operation operation = request.getOperation();
        int quantity = request.getQuantity();

        UserDetails user = (UserDetails) principal;
        
        Order order = orderService.createOrder(user, symbol, operation, quantity);
        return order;
    }
}