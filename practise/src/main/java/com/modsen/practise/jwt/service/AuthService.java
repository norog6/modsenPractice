package com.modsen.practise.jwt.service;

import com.modsen.practise.dto.UserDTO;
import com.modsen.practise.entity.Role;
import com.modsen.practise.jwt.JwtRequest;
import com.modsen.practise.jwt.JwtResponse;
import com.modsen.practise.jwt.JwtUtils;
import com.modsen.practise.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements LogoutHandler {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public UserDTO registration(@NonNull UserDTO user) throws AuthException {
        if (userService.getUserByLogin(user.getLogin()).isPresent()) {
            throw new AuthException("User with this login already exist");
        }
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            throw new AuthException("User with this email already exist");
        }
        user.setRoles(Set.of(Role.CUSTOMER));
        return userService.createUser(user);
    }


    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final UserDTO user = userService.getUserByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User is not found"));
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
                final UserDTO user = userService.getUserByLogin(login)
                        .orElseThrow(() -> new AuthException("User is not found"));
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
                final UserDTO user = userService.getUserByLogin(login)
                        .orElseThrow(() -> new AuthException("User is not found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("JWT token is invalid");
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String token = JwtUtils.getTokenFromRequest(request);
        if (token != null) {
            final Claims claims = jwtProvider.getAccessClaims(token);
            final String login = claims.getSubject();
            refreshStorage.remove(login);
        }
    }
}
