package org.modsen.practise.authentication;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.modsen.practise.model.User;
import org.modsen.practise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Map<String, String> refreshStorage = new HashMap<>();

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String accessToken = jwtUtil.generateAccessToken(userDetails);
        final String newRefreshToken = jwtUtil.generateRefreshToken(userDetails);
        refreshStorage.put(userDetails.getUsername(), newRefreshToken);
        JwtResponse jwtResponse = new JwtResponse(accessToken, newRefreshToken);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) throws AuthException {
        if (jwtUtil.validateToken(refreshToken)) {
            final Claims claims = jwtUtil.extractAllClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                final String accessToken = jwtUtil.generateAccessToken(userDetails);
                final String newRefreshToken = jwtUtil.generateRefreshToken(userDetails);
                refreshStorage.put(userDetails.getUsername(), newRefreshToken);
                JwtResponse jwtResponse = new JwtResponse(accessToken, newRefreshToken);
                return ResponseEntity.ok(jwtResponse);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(" you have access now  ");
    }
}