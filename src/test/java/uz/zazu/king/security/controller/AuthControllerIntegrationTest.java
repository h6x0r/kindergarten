package uz.zazu.king.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import uz.zazu.king.security.common.enums.Role;
import uz.zazu.king.security.dto.LoginRequest;
import uz.zazu.king.security.dto.UserDto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerIntegrationTest {

    private static final String userName = "testSuperAdmin" + UUID.randomUUID().toString().substring(0, 8);
    private static final String password = "testSuperAdminPass";
    private static final Set<String> roles = Set.of(Role.ROLE_ADMIN.name(), Role.ROLE_USER.name());
    private static String jwtToken;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Map<String, LocalDateTime> inactiveTokens;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("Создание пользователя")
    @WithMockUser(roles = "SUPER_ADMIN")
    void createUserSuccess() throws Exception {
        var userDto = UserDto.builder()
                .username(userName)
                .password(password)
                .roles(roles)
                .build();
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    @DisplayName("Логин SUPER_ADMIN")
    void loginWithSuperAdminAccount() throws Exception {
        var loginRequest = LoginRequest.builder()
                .username(userName)
                .password(password)
                .build();
        var result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION))
                .andReturn();
        jwtToken = result.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
    }

    @Test
    @Order(3)
    @DisplayName("Логаут SUPER_ADMIN")
    void logoutSuccess() throws Exception {
        mockMvc.perform(post("/api/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully logged out"));
    }

    @Test
    @Order(4)
    @DisplayName("Создание пользователя после логаута с тем же токеном должно возвращать 401")
    void createUserShouldFailAfterLogout() throws Exception {
        var userDto = UserDto.builder()
                .username(userName)
                .password(password)
                .roles(roles)
                .build();
        var response = mockMvc.perform(post("/api/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));
        response.andExpect(status().isUnauthorized());
    }

    @Test
    @Order(5)
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Удаление тестового пользователя")
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/{name}", userName))
                .andExpect(status().isOk());
    }
}