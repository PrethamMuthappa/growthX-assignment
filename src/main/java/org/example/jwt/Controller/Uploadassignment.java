package org.example.jwt.Controller;

import org.example.jwt.Model.Assignment;
import org.example.jwt.Model.User;
import org.example.jwt.Service.AssignmentService.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/admins")
    public ResponseEntity<Object>fetchalladmin(){
     return new ResponseEntity<>(assignmentService.alladmins(),HttpStatus.OK);
    }

    @GetMapping("/assignment")
    public ResponseEntity<Object>fetchallassignment(Authentication authentication){
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userid=userDetails.getUsername();
        return new ResponseEntity<>(assignmentService.allassignment(userid),HttpStatus.OK);
    }
}
