package com.dusanweb.beba.service;

import com.dusanweb.beba.dto.LoginRequest;
import com.dusanweb.beba.dto.RegisterRequest;
import com.dusanweb.beba.model.Employee;
import com.dusanweb.beba.model.User;
import com.dusanweb.beba.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    //Service interface for encoding passwords.
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signup(RegisterRequest registerRequest) {
        /*
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);
        log.info("User saved : " + user);
        */

/*
        Parent parent = new Parent();
        parent.setFirstName(registerRequest.getFirstName());
        parent.setLastName(registerRequest.getLastName());
        parent.setEmail(registerRequest.getEmail());
        parent.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(parent);
        log.info("User saved : " + parent);
*/
        Employee employee = new Employee();
        employee.setFirstName(registerRequest.getFirstName());
        employee.setLastName(registerRequest.getLastName());
        employee.setEmail(registerRequest.getEmail());
        employee.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        employee.setRoles(registerRequest.getRole());

        userRepository.save(employee);
        log.info("User saved : " + employee.getEmail());

    }

    public Optional<User> loginUser(User user){
        String email = user.getEmail();
        String password = user.getPassword();
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<User> loginUserByEmail(User user){
        String email = user.getEmail();
        return userRepository.findByEmail(email);
    }

    public Optional<User> login(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return userRepository.findByEmailAndPassword(email, password);
    }
}
