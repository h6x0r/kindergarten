package uz.zazu.king.security.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zazu.king.security.common.exception.IncorrectCredentialsException;
import uz.zazu.king.security.dto.LoginRequest;
import uz.zazu.king.security.dto.LoginResponse;
import uz.zazu.king.security.entity.UserEntity;
import uz.zazu.king.security.repository.UserRepository;
import uz.zazu.king.security.service.AuthService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static uz.zazu.king.security.common.enums.Role.ROLE_SUPER_ADMIN;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final Map<String, LocalDateTime> inactiveTokens;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${king.jwt.secret}")
    private String secretBase64;

    public LoginResponse login(LoginRequest loginRequest) {
        verifyUser(loginRequest);
        var userName = loginRequest.getUsername();
        var user = userRepository.findByUserNameAndIsActive(userName);
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        var token = generateToken(user);
        var roles = user.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

        return LoginResponse.builder()
                .roles(roles)
                .jwtToken(token)
                .username(userName)
                .build();
    }

    @Override
    public void logout(String token) {
        inactiveTokens.put(token, LocalDateTime.now());
        log.info("Token invalidated successfully: {}", token);
    }

    private void verifyUser(LoginRequest loginRequest) {
        var user = userRepository.findByUserNameAndIsActive(loginRequest.getUsername());
        if (user == null || !user.isActive()) {
            throw new IncorrectCredentialsException();
        }
    }

    private String generateToken(UserEntity user) {
        var now = Instant.now();
        var expiry = now.plusSeconds(3600);
        var secret = Base64.getDecoder().decode(secretBase64);
        var hmacKey = Keys.hmacShaKeyFor(secret);
        var roles = user.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

        if (roles.contains(ROLE_SUPER_ADMIN.name())) {
            expiry = now.plusSeconds(86400);
        }

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", roles)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(hmacKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Scheduled(fixedRate = 600_000) // Runs every 10 minutes
    public void cleanUpInactiveTokens() {
        var oneHourAgo = LocalDateTime.now().minusHours(1);
        inactiveTokens.entrySet().removeIf(entry -> entry.getValue().isBefore(oneHourAgo));
        log.info("Cleaned up expired inactive tokens.");
    }
}
