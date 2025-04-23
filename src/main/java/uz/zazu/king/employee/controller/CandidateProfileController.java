package uz.zazu.king.employee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileNannyDto;
import uz.zazu.king.employee.service.CandidateProfileService;

import java.util.List;

import static uz.zazu.king.common.Constant.ID_MUST_NOT_BE_NULL_MSG;

/**
 * Контроллер для работы с данными опросников сотрудников.
 * Поддерживает управление данными опросников для бизнес-ассистентов
 * и менеджеров.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidates")
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;

    @Operation(
            summary = "Создание кандидата любого типа",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    oneOf = {
                                            CandidateProfileBusinessDto.class,
                                            CandidateProfileEducatorDto.class,
                                            CandidateProfileNannyDto.class
                                    },
                                    discriminatorProperty = "type"
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "business assistant",
                                            summary = "Кандидат типа 'business' (type = \"business assistant\")",
                                            value = """
                                                    {
                                                      "type": "business assistant",
                                                      "fullName": "Иванов Иван",
                                                      "candidateEntryDate": "2025-01-15T10:00:00",
                                                      "age": 30,
                                                      "contacts": "+7 (999) 123-45-67",
                                                      "candidateStatus": "NEW",
                                                      "resumeLink": "https://example.com/resume/ivanov",
                                                      "previousCompanyName": "ООО Пример",
                                                      "previousPositionName": "Менеджер",
                                                      "responsibilities": "Ведение переговоров...",
                                                      "workingRelationsWithColleagues": "Всегда помогал команде...",
                                                      "multitaskingAndProjectManagement": "Успешно вёл несколько проектов",
                                                      "responsibilityScore": 8,
                                                      "examplesOfLeadership": "Организовывал собственный отдел...",
                                                      "pastSalary": "60 000 руб.",
                                                      "motivationFactors": "Профессиональный рост...",
                                                      "handlingCriticism": "Спокойно воспринимаю...",
                                                      "willingnessToLearnAndDevelop": "Постоянно прохожу курсы...",
                                                      "createdAt": "2025-01-15T10:00:00",
                                                      "updatedAt": "2025-02-01T09:00:00",
                                                      "module": "BUSINESS_ASSISTANT"
                                                    }
                                                    """),
                                    @ExampleObject(
                                            name = "manager",
                                            summary = "Кандидат типа 'business' (type = \"manager\")",
                                            value = """
                                                    {
                                                      "type": "manager",
                                                      "fullName": "Иванов Иван",
                                                      "candidateEntryDate": "2025-01-15T10:00:00",
                                                      "age": 30,
                                                      "contacts": "+7 (999) 123-45-67",
                                                      "candidateStatus": "NEW",
                                                      "resumeLink": "https://example.com/resume/ivanov",
                                                      "previousCompanyName": "ООО Пример",
                                                      "previousPositionName": "Менеджер",
                                                      "responsibilities": "Ведение переговоров...",
                                                      "workingRelationsWithColleagues": "Всегда помогал команде...",
                                                      "multitaskingAndProjectManagement": "Успешно вёл несколько проектов",
                                                      "responsibilityScore": 8,
                                                      "examplesOfLeadership": "Организовывал собственный отдел...",
                                                      "pastSalary": "60 000 руб.",
                                                      "motivationFactors": "Профессиональный рост...",
                                                      "handlingCriticism": "Спокойно воспринимаю...",
                                                      "willingnessToLearnAndDevelop": "Постоянно прохожу курсы...",
                                                      "createdAt": "2025-01-15T10:00:00",
                                                      "updatedAt": "2025-02-01T09:00:00",
                                                      "module": "MANAGER"
                                                    }
                                                    """),
                                    @ExampleObject(
                                            name = "educator",
                                            summary = "Кандидат типа 'educator' (type = \"educator\")",
                                            value = """
                                                    {
                                                      "type": "educator",
                                                      "fullName": "Петров Пётр",
                                                      "candidateEntryDate": "2025-03-10T09:00:00",
                                                      "age": 28,
                                                      "contacts": "+7 (999) 333-22-11",
                                                      "candidateStatus": "IN_PROGRESS",
                                                      "module": "EDUCATOR"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "nanny",
                                            summary = "Класс для кандидата-няни (type = \"nanny\")",
                                            value = """
                                                    {
                                                      "type": "nanny",
                                                      "fullName": "Петрова Анна",
                                                      "candidateEntryDate": "2025-05-10T10:00:00",
                                                      "age": 35,
                                                      "contacts": "+7 (999) 123-45-67",
                                                      "candidateStatus": "NEW",
                                                      "punctuality": true,
                                                      "education": "Высшее педагогическое",
                                                      "salaryExpectations": "50 000 - 60 000 руб.",
                                                      "idealJobDescription": "Работа с детьми 2-3 лет...",
                                                      "thingsThatBringJoy": "Смотреть, как дети развиваются",
                                                      "hasChildren": true,
                                                      "personalInterests": "Психология, чтение",
                                                      "suitabilityRating": 9,
                                                      "finalDecisionAfterInterview": "Кандидат показал...",
                                                      "createdAt": "2025-05-10T10:00:00",
                                                      "updatedAt": "2025-05-10T10:00:00",
                                                      "module": "NANNY"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    )
    @PostMapping
    public CandidateProfileDto create(@NotNull @RequestBody CandidateProfileDto dto) {
        return candidateProfileService.create(dto);
    }

    @GetMapping("/{id}")
    public CandidateProfileDto getById(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id) {
        return candidateProfileService.findById(id);
    }

    @GetMapping
    public List<CandidateProfileDto> getAll() {
        return candidateProfileService.findAll();
    }

    @GetMapping("/business_assistant")
    public List<CandidateProfileBusinessDto> getAllBusinessByBusinessAssistant() {
        return candidateProfileService.findAllByBusinessAssistant();
    }

    @GetMapping("/manager")
    public List<CandidateProfileBusinessDto> getAllBusinessByManager() {
        return candidateProfileService.findAllBusinessByManager();
    }

    @GetMapping("/educator")
    public List<CandidateProfileEducatorDto> getAllEducative() {
        return candidateProfileService.findAllEducative();
    }

    @GetMapping("/nanny")
    public List<CandidateProfileNannyDto> getAllNanny() {
        return candidateProfileService.findAllNanny();
    }

    @PutMapping("/{id}")
    public CandidateProfileDto update(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id, @Valid @RequestBody CandidateProfileDto dto) {
        return candidateProfileService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remove(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id) {
        candidateProfileService.remove(id);
    }
}