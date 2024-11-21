package org.example.growthx.repository;

import org.example.growthx.Model.Role;
import org.example.growthx.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface Userrepos extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);
    List<User> findByRole(Role role);
}

