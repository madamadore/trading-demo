package it.matteoavanzini.example.trading.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document
public class Order {
    private static int ID = 0;

    private String symbol;
    private UserDetails user;
    private Date date;
    private int quantity;
    private double fee;
    private double total;
    private double singlePrice;
    private int id;
    private Operation operation;

    public Order() {
        this.id = ++ID;
    }
}