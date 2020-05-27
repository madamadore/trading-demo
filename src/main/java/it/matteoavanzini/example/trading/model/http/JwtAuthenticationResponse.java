package it.matteoavanzini.example.trading.model.http;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationResponse implements Serializable {

    private final String username;
    Collection<? extends GrantedAuthority> authorities;
    
    public JwtAuthenticationResponse(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return this.username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}