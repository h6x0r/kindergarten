package uz.zazu.king.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.security.common.exception.IncorrectCredentialsException;
import uz.zazu.king.security.common.exception.UserNotFoundException;
import uz.zazu.king.security.dto.AuthFailedResponse;
import uz.zazu.king.security.dto.AuthResponse;
import uz.zazu.king.security.dto.AuthSuccessResponse;
import uz.zazu.king.security.dto.LoginRequest;
import uz.zazu.king.security.service.impl.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            var authResponse = authService.authorizeUser(loginRequest);
            var response = AuthSuccessResponse.builder()
                    .username(authResponse.getUsername())
                    .roles(authResponse.getRoles())
                    .build();
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.AUTHORIZATION, authResponse.getJwtToken())
                    .body(response);
        } catch (UserNotFoundException | IncorrectCredentialsException e) {
            var response = AuthFailedResponse.builder()
                    .msg(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7).trim();
//            authService.blacklistToken(token);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }

}
