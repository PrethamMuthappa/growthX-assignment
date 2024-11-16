package org.example.jwt.Controller;

import org.example.jwt.Model.Assignment;
import org.example.jwt.Service.AssignmentService.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Uploadassignment {

    private final AssignmentService assignmentService;

    public Uploadassignment(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    @PostMapping("/upload")
    public ResponseEntity<?>upload(Authentication authentication, @RequestBody Assignment assignment){
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userid=userDetails.getUsername();
        return new ResponseEntity<>(assignmentService.upload(userid,assignment.getTask(),assignment.getAdmin()), HttpStatus.OK);
    }
}