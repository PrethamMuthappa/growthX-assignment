package org.example.growthx.Controller;


import org.example.growthx.Model.User;
import org.example.growthx.Service.AuthService;
import org.example.growthx.Service.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthencationController {

    private final AuthService authService;

    public AuthencationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User requset){
        return ResponseEntity.ok(authService.register(requset));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User requset){
        return ResponseEntity.ok(authService.authenticate(requset));
    }

    @GetMapping("/check")
    public String test(){
        return "welcome";
    }
}
