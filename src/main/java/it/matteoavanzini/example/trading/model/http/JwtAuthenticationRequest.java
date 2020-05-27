package it.matteoavanzini.example.trading.model.http;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JwtAuthenticationRequest implements Serializable {

    private String username;
    private String password;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}