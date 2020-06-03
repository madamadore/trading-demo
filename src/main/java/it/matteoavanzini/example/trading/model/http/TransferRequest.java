package it.matteoavanzini.example.trading.model.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
    private String symbol;
    private double amount;
    private String fromIban;
    private String toIban;
}