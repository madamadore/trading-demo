package it.matteoavanzini.example.trading.model.http;

import it.matteoavanzini.example.trading.model.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutOrderRequest {
    private String symbol;
    private Operation operation;
    private int quantity;
}