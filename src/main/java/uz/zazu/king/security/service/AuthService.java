package uz.zazu.king.security.service;

import uz.zazu.king.security.dto.LoginRequest;
import uz.zazu.king.security.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    void logout(String token);
}
