package uz.zazu.king.security.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zazu.king.security.common.exception.IncorrectCredentialsException;
import uz.zazu.king.security.dto.AuthGeneratedResponse;
import uz.zazu.king.security.dto.LoginRequest;
import uz.zazu.king.security.entity.UserEntity;
import uz.zazu.king.security.repository.UserRepository;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import static uz.zazu.king.security.common.enums.Role.ROLE_SUPER_ADMIN;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${king.jwt.secret}")
    private String secretBase64;

    public void verifyUser(LoginRequest loginRequest) {
        var user = userRepository.findByUserNameAndIsActive(loginRequest.getUsername());
        if (user == null || !user.isActive()) {
            throw new IncorrectCredentialsException();
        }
    }

    public AuthGeneratedResponse authorizeUser(LoginRequest loginRequest) {
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

        return AuthGeneratedResponse.builder()
                .roles(roles)
                .jwtToken(token)
                .username(userName)
                .build();
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

}
