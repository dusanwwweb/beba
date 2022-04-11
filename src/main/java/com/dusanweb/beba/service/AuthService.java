package com.dusanweb.beba.service;

import com.dusanweb.beba.dto.RegisterRequest;
import com.dusanweb.beba.model.Employee;
import com.dusanweb.beba.model.Parent;
import com.dusanweb.beba.model.User;
import com.dusanweb.beba.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    //Service interface for encoding passwords.
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(user);
        log.info("User saved : " + user);

/*
        Parent parent = new Parent();
        parent.setFirstName(registerRequest.getFirstName());
        parent.setLastName(registerRequest.getLastName());
        parent.setEmail(registerRequest.getEmail());
        parent.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(parent);
        log.info("User saved : " + parent);

        Employee employee = new Employee();
        employee.setFirstName(registerRequest.getFirstName());
        employee.setLastName(registerRequest.getLastName());
        employee.setEmail(registerRequest.getEmail());
        employee.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(employee);
        log.info("User saved : " + employee);
 */
    }
}
