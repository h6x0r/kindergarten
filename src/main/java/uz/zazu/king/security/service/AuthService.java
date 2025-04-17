package uz.zazu.king.security.service;

import uz.zazu.king.security.dto.AuthGeneratedResponse;
import uz.zazu.king.security.dto.LoginRequest;

public interface AuthService {
    AuthGeneratedResponse login(LoginRequest loginRequest);

    void logout(String token);
}
