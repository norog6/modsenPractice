package com.modsen.practise.dto;

import com.modsen.practise.entity.Role;
import lombok.*;

import java.sql.Date;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private long id;


    private String email;


    private String login;


    private String password;


    private String firstName;


    private String lastName;


    private String gender;


    private Date dateOfBirth;

    private Set<Role> roles;

}