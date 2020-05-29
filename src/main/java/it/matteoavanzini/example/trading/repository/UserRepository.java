package it.matteoavanzini.example.trading.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.matteoavanzini.example.trading.model.JwtUser;

public interface UserRepository extends MongoRepository<JwtUser, String> {
    @Query("{'username': ?0}")
    Optional<JwtUser> findByUsername(String username);
}