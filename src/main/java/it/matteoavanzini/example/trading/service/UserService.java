package it.matteoavanzini.example.trading.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.matteoavanzini.example.trading.model.JwtUser;
import it.matteoavanzini.example.trading.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) return user.get();
        else throw new UsernameNotFoundException("Credentials not valid");
    }

}