package uz.zazu.king.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.security.dto.AuthResponse;
import uz.zazu.king.security.dto.LoginRequest;
import uz.zazu.king.security.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        var authResponse = authService.login(loginRequest);
        var response = AuthResponse.builder()
                .username(authResponse.getUsername())
                .roles(authResponse.getRoles())
                .build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, authResponse.getJwtToken())
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String token) {
        authService.logout(token);
        return ResponseEntity.ok("Successfully logged out");
    }

}
