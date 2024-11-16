package org.example.jwt.repository;

import org.example.jwt.Model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepo extends MongoRepository<Assignment, String> {
    List<Assignment> findByAdmin(String adminUsername);
}
