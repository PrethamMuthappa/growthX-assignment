package org.example.growthx.Service.AssignmentService;

import org.example.growthx.Model.Assignment;
import org.example.growthx.Model.Role;
import org.example.growthx.Model.User;
import org.example.growthx.repository.AssignmentRepo;
import org.example.growthx.repository.Userrepos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepo assignmentRepo;
    private final Userrepos userrepos;

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
        assignment.setCreatedAt(new Date());

        Assignment savedAssignment = assignmentRepo.save(assignment);
        return ResponseEntity.ok(savedAssignment);
    }

    public List<String> alladmins() {

        return userrepos.findByRole(Role.ADMIN)
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList()).reversed();

    }

    public ResponseEntity<?> allassignment(String userid) {
        // Check if user exists and is admin
        User admin = userrepos.findUserByUsername(userid)
                .orElse(null);

        if (admin == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!admin.getRole().equals(Role.ADMIN)) {
            return ResponseEntity.badRequest().body("Specified user is not an admin");
        }

        // Fetch all assignments for this admin
        List<Assignment> assignments = assignmentRepo.findByAdmin(userid);
        return ResponseEntity.ok(assignments);
    }

    public ResponseEntity<?> acceptAssignment(String assignmentId, String adminId) {
        // Check if admin exists and has admin role
        User admin = userrepos.findUserByUsername(adminId)
                .orElse(null);

        if (admin == null || !admin.getRole().equals(Role.ADMIN)) {
            return ResponseEntity.badRequest().body("User is not authorized to accept assignments");
        }

        // Find the assignment
        Assignment assignment = assignmentRepo.findById(assignmentId)
                .orElse(null);

        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }

        // Verify this admin is assigned to this assignment
        if (!assignment.getAdmin().equals(adminId)) {
            return ResponseEntity.badRequest().body("Not authorized to accept this assignment");
        }

        // Update status
        assignment.setStatus("ACCEPTED");
        Assignment updatedAssignment = assignmentRepo.save(assignment);

        return ResponseEntity.ok(updatedAssignment);
    }

    public ResponseEntity<?> declineAssignment(String assignmentId, String adminId) {
        // Check if admin exists and has admin role
        User admin = userrepos.findUserByUsername(adminId)
                .orElse(null);

        if (admin == null || !admin.getRole().equals(Role.ADMIN)) {
            return ResponseEntity.badRequest().body("User is not authorized to decline assignments");
        }

        // Find the assignment
        Assignment assignment = assignmentRepo.findById(assignmentId)
                .orElse(null);

        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }

        // Verify this admin is assigned to this assignment
        if (!assignment.getAdmin().equals(adminId)) {
            return ResponseEntity.badRequest().body("Not authorized to decline this assignment");
        }

        // Update status
        assignment.setStatus("DECLINED");
        Assignment updatedAssignment = assignmentRepo.save(assignment);

        return ResponseEntity.ok(updatedAssignment);
    }


}
