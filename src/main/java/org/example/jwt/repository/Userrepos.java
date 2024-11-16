package org.example.jwt.repository;

import org.example.jwt.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface Userrepos extends MongoRepository<User , String> {
    Optional<User>findUserByUsername(String username);
}

