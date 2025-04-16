package uz.zazu.king.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static String jwtToken;
    private static final String userName = "testSuperAdmin" + UUID.randomUUID().toString().substring(0, 8);
    private static final String password = "testSuperAdminPass";
    private static final Set<String> roles = Set.of(Role.ROLE_ADMIN.name(), Role.ROLE_USER.name());

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
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().isOk());
    }

//    @Test
//    @Order(4)
//    @DisplayName("Создание пользователя после логаута с тем же токеном должно возвращать 401")
//    void createUserShouldFailAfterLogout() throws Exception {
//        var userDto = UserDto.builder()
//                .username(userName)
//                .password(password)
//                .roles(roles)
//                .build();
//        var response = mockMvc.perform(post("/api/users")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userDto)));
//        response.andExpect(status().isUnauthorized());
//    }

    @Test
    @Order(5)
    @DisplayName("Удаление тестового пользователя")
    void deleteUser () throws Exception {
        mockMvc.perform(delete("/api/users/{name}", userName))
                .andExpect(status().isOk());
    }
}