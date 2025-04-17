package uz.zazu.king.lead.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import uz.zazu.king.lead.common.enums.LeadState;
import uz.zazu.king.lead.dto.LeadDto;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Tests for LeadController")
public class LeadControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private LeadDto dto;

    @BeforeEach
    void setUp() {
        dto = LeadDto.builder()
                .parentName("Ольга Петрова")
                .parentPhone("+998999999999")
                .childName("Александр")
                .childAge(3)
                .leadSource("Instagram")
                .status(LeadState.NEW_LEAD)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Nested
    @DisplayName("Create Lead Tests")
    class CreateLeadTests {

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Позитивный тест: создание лида")
        void testCreateLeadPositive() throws Exception {
            String content = objectMapper.writeValueAsString(dto);

            mockMvc.perform(post("/api/leads")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", not(isEmptyString())));
        }

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Негативный тест: создание лида без обязательных полей")
        void testCreateLeadNegative() throws Exception {
            String content = objectMapper.writeValueAsString(new LeadDto());

            mockMvc.perform(post("/api/leads")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Get Lead By ID Tests")
    class GetLeadByIdTests {

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Позитивный тест: поиск лида по ID с добавлением и удалением")
        void testGetLeadByIdPositive() throws Exception {
            String createPayload = objectMapper.writeValueAsString(dto);

            MvcResult createResult = mockMvc.perform(post("/api/leads")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createPayload))
                    .andExpect(status().isOk())
                    .andReturn();

            String createResponseContent = createResult.getResponse().getContentAsString();
            assertNotNull(createResponseContent);

            // Вынимаем id из JSON-ответа
            String createdId = createResponseContent.replaceAll(".*\"id\":\"(.*?)\".*", "$1");
            assertNotNull(createdId);

            // Проверяем, что по этому id можно получить лида
            mockMvc.perform(get("/api/leads/{id}", createdId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(createdId));

            // Чистим за собой
            mockMvc.perform(delete("/api/leads/{id}", createdId))
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Негативный тест: поиск лида по несуществующему ID")
        void testGetLeadByIdNegative() throws Exception {
            mockMvc.perform(get("/api/leads/{id}", "invalidId"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Get All Leads Tests")
    class GetAllLeadsTests {

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Позитивный тест: получение всех лидов")
        void testGetAllLeadsPositive() throws Exception {
            mockMvc.perform(get("/api/leads"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Негативный тест: запрос к несуществующему эндпоинту")
        void testGetAllLeadsNegative() throws Exception {
            mockMvc.perform(get("/api/unknown-leads"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Update Lead Tests")
    class UpdateLeadTests {

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Позитивный тест: обновление лида (добавление и удаление)")
        void testUpdateLeadPositive() throws Exception {
            // Сначала создадим лид
            String createPayload = objectMapper.writeValueAsString(dto);

            MvcResult createResult = mockMvc.perform(post("/api/leads")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createPayload))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseContent = createResult.getResponse().getContentAsString();
            String createdId = responseContent.replaceAll(".*\"id\":\"(.*?)\".*", "$1");

            // Подготовим DTO для обновления
            LeadDto updateDto = LeadDto.builder()
                    .id(createdId)
                    .parentName("Updated Name")
                    .childName("Степан")
                    .childAge(6)
                    .status(LeadState.TOUR_APPOINTMENT)
                    .leadSource("Website")
                    .build();

            String updatePayload = objectMapper.writeValueAsString(updateDto);

            // Выполним обновление
            mockMvc.perform(put("/api/leads/{id}", createdId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updatePayload))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.parentName").value("Updated Name"))
                    .andExpect(jsonPath("$.childAge").value(6));

            // Удаляем созданную запись
            mockMvc.perform(delete("/api/leads/{id}", createdId))
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Негативный тест: обновление лида по несуществующему ID")
        void testUpdateLeadNegative() throws Exception {
            String updatePayload = objectMapper.writeValueAsString(dto);

            mockMvc.perform(put("/api/leads/{id}", "fakeId")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updatePayload))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Delete Lead Tests")
    class DeleteLeadTests {

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Позитивный тест: удаление лида")
        void testDeleteLeadPositive() throws Exception {
            // Сначала создаём лид
            String createPayload = objectMapper.writeValueAsString(dto);

            MvcResult createResult = mockMvc.perform(post("/api/leads")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createPayload))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseContent = createResult.getResponse().getContentAsString();
            String createdId = responseContent.replaceAll(".*\"id\":\"(.*?)\".*", "$1");

            // Удаляем лид
            mockMvc.perform(delete("/api/leads/{id}", createdId))
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser(roles = "SUPER_ADMIN")
        @DisplayName("Негативный тест: удаление лида с несуществующим ID")
        void testDeleteLeadNegative() throws Exception {
            mockMvc.perform(delete("/api/leads/{id}", "nonExistentId"))
                    .andExpect(status().isNotFound());
        }
    }
}
