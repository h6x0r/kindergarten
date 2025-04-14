package uz.zazu.king.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CandidateProfileControllerIntegrationTest {

    private static final String BASE_URL = "/api/questionnaire/employee";
    private static CandidateProfileBusinessDto businessDto;
    private static CandidateProfileEducatorDto educativeDto;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        businessDto = CandidateProfileBusinessDto.builder()
                .fullName("Тестовый Пользователь (Business)")
                .age(30)
                .build();

        educativeDto = CandidateProfileEducatorDto.builder()
                .fullName("Тестовый Пользователь (Educative)")
                .age(28)
                .build();
    }

    @Nested
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Метод create()")
    class CreateMethodTests {

        @Test
        @DisplayName("Позитивный сценарий создания (Business)")
        void createPositiveBusiness() throws Exception {
            mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(businessDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.fullName").value("Тестовый Пользователь (Business)"))
                    .andExpect(jsonPath("$.age").value(30));
        }
    }

    @Nested
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Метод create() - Educative")
    class CreateEducativeMethodTests {

        @Test
        @DisplayName("Позитивный сценарий создания (Educative)")
        void createPositiveEducative() throws Exception {
            mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(educativeDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.fullName").value("Тестовый Пользователь (Educative)"))
                    .andExpect(jsonPath("$.age").value(28));
        }
    }

    @Nested
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Метод getById()")
    class GetByIdMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: найденный объект (Business)")
        void getByIdPositiveBusiness() throws Exception {
            var savedEntity = mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(businessDto)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            var savedId = objectMapper.readTree(savedEntity).get("id").asText();

            try {
                mockMvc.perform(get(BASE_URL + "/{id}", savedId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(savedId));
            } finally {
                mockMvc.perform(delete(BASE_URL + "/{id}", savedId))
                        .andExpect(status().isOk());
            }
        }

        @Test
        @DisplayName("Негативный сценарий: несуществующий объект")
        void getByIdNegative_NotFound() throws Exception {
            String invalidId = "not_existing_id";

            mockMvc.perform(get(BASE_URL + "/{id}", invalidId))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Метод getById() - Educative")
    class GetByIdEducativeMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: найденный объект (Educative)")
        void getByIdPositiveEducative() throws Exception {
            var savedEntity = mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(educativeDto)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            var savedId = objectMapper.readTree(savedEntity).get("id").asText();

            try {
                mockMvc.perform(get(BASE_URL + "/{id}", savedId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(savedId))
                        .andExpect(jsonPath("$.fullName").value("Тестовый Пользователь (Educative)"));
            } finally {
                mockMvc.perform(delete(BASE_URL + "/{id}", savedId))
                        .andExpect(status().isOk());
            }
        }

        @Test
        @DisplayName("Негативный сценарий: несуществующий объект (Educative)")
        void getByIdNegativeEducative_NotFound() throws Exception {
            String invalidId = "non_existent_educative_id";

            mockMvc.perform(get(BASE_URL + "/{id}", invalidId))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Метод getAll()")
    class GetAllMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: список возвращается")
        void getAllPositive() throws Exception {
            mockMvc.perform(get(BASE_URL))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        @DisplayName("Негативный сценарий: некорректный путь (403)")
        void getAllNegative_NotFound() throws Exception {
            mockMvc.perform(get(BASE_URL + "123"))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Метод getAllBusiness()")
    class GetAllBusinessMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: возвращаем список бизнес-опросников")
        void getAllBusinessPositive() throws Exception {
            mockMvc.perform(get(BASE_URL + "/business"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        @DisplayName("Негативный сценарий: некорректный путь")
        void getAllBusinessNegative_BadRequest() throws Exception {
            mockMvc.perform(get(BASE_URL + "/business123"))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @WithMockUser(roles = "SUPER_ADMIN")
    @DisplayName("Метод getAllEducative()")
    class GetAllEducativeMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: возвращаем список образовательных опросников")
        void getAllEducativePositive() throws Exception {
            mockMvc.perform(get(BASE_URL + "/educative"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        @DisplayName("Негативный сценарий: некорректный URL")
        void getAllEducativeNegative_BadRequest() throws Exception {
            mockMvc.perform(get(BASE_URL + "/educative_not_exists"))
                    .andExpect(status().is4xxClientError());
        }
    }
}