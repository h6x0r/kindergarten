package uz.zazu.king.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uz.zazu.king.employee.dto.QuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.QuestionnaireEducativeRoleDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionnaireControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("Метод create()")
    class CreateMethodTests {

        @Test
        @DisplayName("Позитивный сценарий создания (Business)")
        void createPositiveBusiness() throws Exception {
            QuestionnaireBusinessRoleDto dto = QuestionnaireBusinessRoleDto.builder()
                    .fullName("Иванов Иван")
                    .age(30)
                    .build();

            mockMvc.perform(post("/api/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.fullName").value("Иванов Иван"))
                    .andExpect(jsonPath("$.age").value(30));
        }
    }

    @Nested
    @DisplayName("Метод create() - Educative")
    class CreateEducativeMethodTests {

        @Test
        @DisplayName("Позитивный сценарий создания (Educative)")
        void createPositiveEducative() throws Exception {
            QuestionnaireEducativeRoleDto dto = QuestionnaireEducativeRoleDto.builder()
                    .fullName("Петров Пётр")
                    .age(28)
                    .education("Высшее педагогическое")
                    .reasonForWorkingInThisField("Люблю работать с детьми")
                    .build();

            mockMvc.perform(post("/api/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.fullName").value("Петров Пётр"))
                    .andExpect(jsonPath("$.age").value(28))
                    .andExpect(jsonPath("$.education").value("Высшее педагогическое"))
                    .andExpect(jsonPath("$.reasonForWorkingInThisField").value("Люблю работать с детьми"));
        }
    }

    @Nested
    @DisplayName("Метод getById()")
    class GetByIdMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: найденный объект (Business)")
        void getByIdPositiveBusiness() throws Exception {
            QuestionnaireBusinessRoleDto dto = QuestionnaireBusinessRoleDto.builder()
                    .fullName("Тест Пользователь Должен Быть Неактивен")
                    .age(25)
                    .build();

            var savedEntity = mockMvc.perform(post("/api/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            var savedId = objectMapper.readTree(savedEntity).get("id").asText();

            try {
                mockMvc.perform(get("/api/questionnaire/{id}", savedId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(savedId));
            } finally {
                mockMvc.perform(delete("/api/questionnaire/{id}", savedId))
                        .andExpect(status().isOk());
            }
        }

        @Test
        @DisplayName("Негативный сценарий: несуществующий объект")
        void getByIdNegative_NotFound() throws Exception {
            String invalidId = "not_existing_id";

            mockMvc.perform(get("/api/questionnaire/{id}", invalidId))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @DisplayName("Метод getById() - Educative")
    class GetByIdEducativeMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: найденный объект (Educative)")
        void getByIdPositiveEducative() throws Exception {
            QuestionnaireEducativeRoleDto dto = QuestionnaireEducativeRoleDto.builder()
                    .fullName("Тестовый Пользователь (Educative)")
                    .age(29)
                    .education("Среднее специальное")
                    .build();

            var savedEntity = mockMvc.perform(post("/api/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            var savedId = objectMapper.readTree(savedEntity).get("id").asText();

            try {
                mockMvc.perform(get("/api/questionnaire/{id}", savedId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(savedId))
                        .andExpect(jsonPath("$.fullName").value("Тестовый Пользователь (Educative)"));
            } finally {
                mockMvc.perform(delete("/api/questionnaire/{id}", savedId))
                        .andExpect(status().isOk());
            }
        }

        @Test
        @DisplayName("Негативный сценарий: несуществующий объект (Educative)")
        void getByIdNegativeEducative_NotFound() throws Exception {
            String invalidId = "non_existent_educative_id";

            mockMvc.perform(get("/api/questionnaire/{id}", invalidId))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @DisplayName("Метод getAll()")
    class GetAllMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: список возвращается")
        void getAllPositive() throws Exception {
            mockMvc.perform(get("/api/questionnaire"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        @DisplayName("Негативный сценарий: некорректный путь (403)")
        void getAllNegative_NotFound() throws Exception {
            mockMvc.perform(get("/api/questionnaire123"))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @DisplayName("Метод getAllBusiness()")
    class GetAllBusinessMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: возвращаем список бизнес-опросников")
        void getAllBusinessPositive() throws Exception {
            mockMvc.perform(get("/api/questionnaire/business"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        @DisplayName("Негативный сценарий: некорректный путь")
        void getAllBusinessNegative_BadRequest() throws Exception {
            mockMvc.perform(get("/api/questionnaire/business123"))
                    .andExpect(status().is4xxClientError());
        }
    }

    @Nested
    @DisplayName("Метод getAllEducative()")
    class GetAllEducativeMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: возвращаем список образовательных опросников")
        void getAllEducativePositive() throws Exception {
            mockMvc.perform(get("/api/questionnaire/educative"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        @DisplayName("Негативный сценарий: некорректный URL")
        void getAllEducativeNegative_BadRequest() throws Exception {
            mockMvc.perform(get("/api/questionnaire/educative_not_exists"))
                    .andExpect(status().is4xxClientError());
        }
    }
}