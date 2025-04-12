package uz.zazu.king.lead.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.lead.dto.LeadQuestionnaireDto;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Tests for LeadQuestionnaireController")
public class LeadQuestionnaireControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private LeadQuestionnaireDto testDto;

    @BeforeEach
    void setUp() {
        testDto = LeadQuestionnaireDto.builder()
                .id(null)
                .orgId(1L)
                .firstName("John")
                .lastName("Doe")
                .patronymic("Timurovich")
                .groupAndTeacher("Группа A, преподаватель Иванова")
                .ltv(1000L)
                .childPickupPerson("Jack Smith")
                .parentFullName("Jane Brown")
                .childAgeAndDOB("3 года, 2020-01-01")
                .childSpecialNeeds("Нет")
                .parentContact("+1234567890")
                .parentAge("30")
                .sourceInfo("Интернет")
                .parentJobAndPosition("Инженер")
                .priorities("Удобное расположение, уют")
                .leavingReason("Переезд")
                .rating(8)
                .improvementSuggestions("Улучшение качества обучения")
                .childSatisfactionLevel("Высокий")
                .gardenSearchMethod("Поиск в интернете")
                .psychologistTopics("N/A")
                .parentAdvice("Присматриваться к отзывам")
                .recommendationImprovements("На данный момент нет")
                .reasonForChoosing("Находится рядом с домом")
                .uzbekBloggers("some blogger")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Nested
    @DisplayName("Create Lead Questionnaire Tests")
    class CreateTests {

        @Test
        @DisplayName("Позитивный: Создание нового опроса")
        @WithMockUser(roles = "SUPER_ADMIN")
        void createLeadQuestionnaire_Positive() throws Exception {
            mockMvc.perform(post("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.firstName").value("John"))
                    .andExpect(jsonPath("$.lastName").value("Doe"));
        }

        @Test
        @DisplayName("Негативный: Создание нового опроса без авторизации -> ожидаем 403")
        void createLeadQuestionnaire_Negative_Unauthorized() throws Exception {
            mockMvc.perform(post("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testDto)))
                    .andExpect(status().isForbidden());
        }

        @Test
        @DisplayName("Негативный: Создание нового опроса с отсутствующими обязательными полями -> 400")
        @WithMockUser(roles = "SUPER_ADMIN")
        void createLeadQuestionnaire_Negative_MissingRequiredField() throws Exception {
            var invalidDto = objectMapper.readValue(
                    objectMapper.writeValueAsString(new LeadQuestionnaireDto()),
                    LeadQuestionnaireDto.class
            );

            mockMvc.perform(post("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(invalidDto)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Get All Lead Questionnaires Tests")
    class GetAllTests {

        @Test
        @DisplayName("Позитивный: Получение всех опросов")
        @WithMockUser(roles = "SUPER_ADMIN")
        void getAllLeadQuestionnaires_Positive() throws Exception {
            mockMvc.perform(get("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray());
        }

        @Test
        @DisplayName("Негативный: Попытка получить все опросы без авторизации -> 403")
        void getAllLeadQuestionnaires_Negative_Unauthorized() throws Exception {
            mockMvc.perform(get("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Get Lead Questionnaire By ID Tests")
    class GetByIdTests {

        @Test
        @DisplayName("Позитивный: Получение опроса по ID")
        @WithMockUser(roles = "SUPER_ADMIN")
        void getLeadQuestionnaireById_Positive() throws Exception {
            var createdResponse = mockMvc.perform(post("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testDto)))
                    .andExpect(status().isOk())
                    .andReturn();

            var createdJson = createdResponse.getResponse().getContentAsString();
            var createdDto = objectMapper.readValue(createdJson, LeadQuestionnaireDto.class);
            var id = createdDto.getId();

            mockMvc.perform(get("/api/leads/questionnaire/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id));

            // Чистим за собой
            mockMvc.perform(delete("/api/leads/questionnaire/{id}", id))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Негативный: Запрос опроса по несуществующему ID -> 404")
        @WithMockUser(roles = "SUPER_ADMIN")
        void getLeadQuestionnaireById_Negative_NotFound() throws Exception {
            mockMvc.perform(get("/api/leads/questionnaire/{id}", 999999L)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Update Lead Questionnaire Tests")
    class UpdateTests {

        @Test
        @DisplayName("Позитивный: Обновление опроса")
        @WithMockUser(roles = "SUPER_ADMIN")
        void updateLeadQuestionnaire_Positive() throws Exception {
            var savedEntity = mockMvc.perform(post("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testDto)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            var savedId = objectMapper.readTree(savedEntity).get("id").asText();

            testDto.setFirstName("Jane");

            mockMvc.perform(put("/api/leads/questionnaire/{id}", savedId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.firstName").value("Jane"));

            // Чистим за собой
            mockMvc.perform(delete("/api/leads/questionnaire/{id}", savedId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Негативный: Обновление несуществующего опроса -> 404")
        @WithMockUser(roles = "SUPER_ADMIN")
        void updateLeadQuestionnaire_Negative_NotFound() throws Exception {
            mockMvc.perform(put("/api/leads/questionnaire/{id}", 999999L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testDto)))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Delete Lead Questionnaire Tests")
    class DeleteTests {

        @Test
        @DisplayName("Позитивный: Удаление опроса")
        @WithMockUser(roles = "SUPER_ADMIN")
        void deleteLeadQuestionnaire_Positive() throws Exception {
            var createdResponse = mockMvc.perform(post("/api/leads/questionnaire")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(testDto)))
                    .andExpect(status().isOk())
                    .andReturn();

            var createdJson = createdResponse.getResponse().getContentAsString();
            var createdDto = objectMapper.readValue(createdJson, LeadQuestionnaireDto.class);
            var id = createdDto.getId();

            mockMvc.perform(delete("/api/leads/questionnaire/{id}", id))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Негативный: Удаление опроса без авторизации -> 403")
        void deleteLeadQuestionnaire_Negative_Unauthorized() throws Exception {
            mockMvc.perform(delete("/api/leads/questionnaire/{id}", 1L))
                    .andExpect(status().isForbidden());
        }
    }
}