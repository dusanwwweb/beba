package com.dusanweb.beba.dto;

import com.dusanweb.beba.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /*
    DTO stands for Data Transfer Object, which is a design pattern.
    It is one of the EPA patterns which we call when we need to use
    such objects that encapsulate and aggregate data for transfer.
    */
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> role;
}
