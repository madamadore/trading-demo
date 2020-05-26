# Trading Demo

This project is a demo for implementing REST Service with Spring Boot

A _Trading System_ is micro-service is a system that offers to users the trade assets online.
An _asset_ is a stock exchange and its value is represented during the time by a _candle_, 
that is an object with 4 different prices, a time value and a _length_.

For simplicity, **in this projet all candles has the fixed _length_ (duration) of 24 hours**.

![Example of candles](https://github.com/madamadore/trading-demo/blob/master/images/candle-sample.png?raw=true)

## Use Case

A User will be able to:

- GET a list of candles of a particular asset (_symbol_), between dates
- Login to the sytem (for future development)

![Very simple Use-Case](https://github.com/madamadore/trading-demo/blob/master/images/use-case.jpg?raw=true)