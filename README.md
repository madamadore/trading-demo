# Trading Demo

This project is a demo for implementing REST Service with Spring Boot

A _Trading System_ is a micro-service system that offers the ability to trade assets online.
An _asset_ is a stock exchange and its value is represented during the time by a _candle_, 
that is an object that represents a _slice of time_, with some attributes:

- open price: the price of the asset at the start of the candle
- close price:the price of the asset at the end of the candle
- maximum price: the maximum price reached by asset during the time
- minium price: the minimum price that by the asset during the time
- duration: the duration of time's slice
- open time: the time when candle starts
- volume: the number of trades during the candle

For simplicity, **in this projet all candles has the fixed _length_ (duration) of 24 hours**.

![Example of candles](https://github.com/madamadore/trading-demo/blob/master/images/candle-sample.png?raw=true)

## Use Case

A User will be able to:

- GET a list of candles of a particular asset (_symbol_), between dates
- Login to the sytem (for future development)

![Very simple Use-Case](https://github.com/madamadore/trading-demo/blob/master/images/use-case.jpg?raw=true)