package it.matteoavanzini.example.trading.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badCredentialsError(Exception err) {
        return ResponseEntity.badRequest().body("Bad credentials");
    }
}