package org.modsen.practise.authentication;

import lombok.Getter;

@Getter
class JwtRequest {
    private String username;
    private String password;
}
