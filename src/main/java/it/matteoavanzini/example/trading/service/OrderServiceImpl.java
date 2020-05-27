package it.matteoavanzini.example.trading.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import it.matteoavanzini.example.trading.model.Operation;
import it.matteoavanzini.example.trading.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${trading.trade.fee}")
    private double fee;

    @Override
    public Order createOrder(String symbol, Operation op, int quantity) {
        Order order = new Order();
        double singlePrice = getSinglePrice();
        
        // UserDetails userDetails =
        //         (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // UserDetails user = inMemoryUserDetailsManager.loadUserByUsername(userDetails.getUsername());

        double total = singlePrice * quantity;
        double feeValue = ((total * fee) / 100);
        // order.setUser(userDetails);
        order.setSymbol(symbol);
        order.setDate(new Date());
        order.setQuantity(quantity);
        order.setSinglePrice(singlePrice);
        order.setTotal(total + feeValue);
        order.setFee(feeValue);
        order.setOperation(op);
        return order;
    }
    
    private double getSinglePrice() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextDouble() * 100;
    }
}