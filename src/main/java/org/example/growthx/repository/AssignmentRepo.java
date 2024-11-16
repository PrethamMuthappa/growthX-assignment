package org.example.growthx.repository;

import org.example.growthx.Model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepo extends MongoRepository<Assignment, String> {
    List<Assignment> findByAdmin(String adminUsername);
    Optional<Assignment> findById(String id);
}
