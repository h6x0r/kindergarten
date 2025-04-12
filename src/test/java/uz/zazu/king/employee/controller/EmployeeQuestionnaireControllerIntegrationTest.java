package uz.zazu.king.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uz.zazu.king.employee.dto.EmployeeEmployeeQuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireEducativeRoleDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeQuestionnaireControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/questionnaire/employee";

    @Nested
    @DisplayName("Метод create()")
    class CreateMethodTests {

        @Test
        @DisplayName("Позитивный сценарий создания (Business)")
        void createPositiveBusiness() throws Exception {
            EmployeeEmployeeQuestionnaireBusinessRoleDto dto = EmployeeEmployeeQuestionnaireBusinessRoleDto.builder()
                    .fullName("Иванов Иван")
                    .age(30)
                    .build();

            mockMvc.perform(post(BASE_URL)
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
            EmployeeQuestionnaireEducativeRoleDto dto = EmployeeQuestionnaireEducativeRoleDto.builder()
                    .fullName("Петров Пётр")
                    .age(28)
                    .education("Высшее педагогическое")
                    .reasonForWorkingInThisField("Люблю работать с детьми")
                    .build();

            mockMvc.perform(post(BASE_URL)
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
            EmployeeEmployeeQuestionnaireBusinessRoleDto dto = EmployeeEmployeeQuestionnaireBusinessRoleDto.builder()
                    .fullName("Тест Пользователь Должен Быть Неактивен")
                    .age(25)
                    .build();

            var savedEntity = mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
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
    @DisplayName("Метод getById() - Educative")
    class GetByIdEducativeMethodTests {

        @Test
        @DisplayName("Позитивный сценарий: найденный объект (Educative)")
        void getByIdPositiveEducative() throws Exception {
            EmployeeQuestionnaireEducativeRoleDto dto = EmployeeQuestionnaireEducativeRoleDto.builder()
                    .fullName("Тестовый Пользователь (Educative)")
                    .age(29)
                    .education("Среднее специальное")
                    .build();

            var savedEntity = mockMvc.perform(post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
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