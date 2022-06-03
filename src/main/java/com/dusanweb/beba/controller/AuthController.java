package com.dusanweb.beba.controller;

import com.dusanweb.beba.dto.LoginRequest;
import com.dusanweb.beba.dto.RegisterRequest;
import com.dusanweb.beba.model.User;
import com.dusanweb.beba.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    //ResponseEntity represents the whole HTTP response: status code, headers, and body
    //ResponseEntity is a generic type
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/login/user")
    public Optional<User> loginUser(@RequestBody User user) {
        return authService.loginUser(user);
    }

    @PostMapping("/login/email")
    public Optional<User> loginUserByEmail(@RequestBody User user) {
        return authService.loginUserByEmail(user);
    }

}
