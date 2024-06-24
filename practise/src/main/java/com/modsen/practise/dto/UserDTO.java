package com.modsen.practise.dto;

import com.modsen.practise.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Login is mandatory")
    @Size(min = 8, max = 20, message = "Login should be between 8 and 20 characters")
    private String login;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 60, message = "First name should be between 3 and 60 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 60, message = "Last name should be between 3 and 60 characters")
    private String lastName;

    @NotBlank(message = "Gender is mandatory")
    @Size(min = 3, max = 60, message = "Gender should be between 3 and 60 characters")
    private String gender;

    @NotNull(message = "Date of Birth is mandatory")
    private Date dateOfBirth;

    private Set<Role> roles;
//
//    private List<OrderDTO> orders;
}