package com.modsen.practise.jwt.service;

import com.modsen.practise.dto.UserDTO;
import com.modsen.practise.entity.Role;
import com.modsen.practise.jwt.JwtRequest;
import com.modsen.practise.jwt.JwtResponse;
import com.modsen.practise.repository.UserRepository;
import com.modsen.practise.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final UserDTO user = new UserDTO(1, "asdasdad", "nikita", "12345", new HashSet<>(List.of(Role.CUSTOMER)), null);/*userService.getUserByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User is not found"));*/
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Incorrect password");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final UserDTO user = new UserDTO(1, "asdasdad", "nikita", "12345", null, null);
                /*final UserDTO user = userService.getUserByLogin(login)
                        .orElseThrow(() -> new AuthException("User is not found"));*/
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final UserDTO user = new UserDTO(1, "asdasdad", "nikita", "12345", null, null);
                /*final UserDTO user = userService.getUserByLogin(login)
                        .orElseThrow(() -> new AuthException("User is not found"));*/
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("JWT token is invalid");
    }

}