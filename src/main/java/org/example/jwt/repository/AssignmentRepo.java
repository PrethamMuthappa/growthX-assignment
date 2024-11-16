package org.example.jwt.repository;

import org.example.jwt.Model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepo extends MongoRepository<Assignment, String> {
}
