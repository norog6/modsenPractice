package com.modsen.practise.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    @NotBlank(message = "Missing login")
    private String login;

    @NotBlank(message = "Missing password")
    private String password;
}