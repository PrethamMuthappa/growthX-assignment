package org.example.jwt.Service.AssignmentService;

import org.example.jwt.Model.Assignment;
import org.example.jwt.Model.Role;
import org.example.jwt.Model.User;
import org.example.jwt.repository.AssignmentRepo;
import org.example.jwt.repository.Userrepos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepo assignmentRepo;
    private final Userrepos  userrepos;

    public AssignmentService(AssignmentRepo assignmentRepo, Userrepos userrepos) {
        this.assignmentRepo = assignmentRepo;
        this.userrepos = userrepos;
    }

    public ResponseEntity<?> upload(String userId, String task, String adminUsername) {
        // Check if task is empty or not 
        if (task == null || task.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Task cannot be empty");
        }

        //check if admin exists and has ADMIN role
        User admin = userrepos.findUserByUsername(adminUsername)
                .orElse(null);
        
        if (admin == null) {
            return ResponseEntity.badRequest().body("Admin user not found");
        }
        
        if (!admin.getRole().equals(Role.ADMIN)) {
            return ResponseEntity.badRequest().body("Specified user is not an admin");
        }
        
        Assignment assignment = new Assignment();
        assignment.setUserId(userId);
        assignment.setTask(task);
        assignment.setAdmin(adminUsername);

        Assignment savedAssignment = assignmentRepo.save(assignment);
        return ResponseEntity.ok(savedAssignment);
    }

    public List<String> alladmins() {

        return userrepos.findByRole(Role.ADMIN)
        .stream()
        .map(User::getUsername)
        .collect(Collectors.toList()).reversed();

    }
}
