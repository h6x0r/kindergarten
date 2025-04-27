package uz.zazu.king.questionnaire.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.zazu.king.questionnaire.dto.LeadQuestionnaireDto;
import uz.zazu.king.questionnaire.service.LeadQuestionnaireService;

import java.util.List;

import static uz.zazu.king.common.Constant.ID_MUST_NOT_BE_NULL_MSG;


/**
 * Обрабатывает операции, связанные с опросниками по анкете custdev.
 * Включает следующие опросники:
 * - ДОУ АлАнга
 * - ДОУ Мирабад
 * - Почемучка АлАнга
 */
@RestController
@RequestMapping("/api/questionnaire/leads")
@RequiredArgsConstructor
public class LeadQuestionnaireController {

    private final LeadQuestionnaireService leadQuestionnaireService;

    @PostMapping
    public LeadQuestionnaireDto create(@Valid @RequestBody LeadQuestionnaireDto lead) {
        return leadQuestionnaireService.create(lead);
    }

    @GetMapping("/{id}")
    public LeadQuestionnaireDto getById(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id) {
        return leadQuestionnaireService.findById(id);
    }

    @GetMapping
    public List<LeadQuestionnaireDto> getAll() {
        return leadQuestionnaireService.findAll();
    }

    @PutMapping("/{id}")
    public LeadQuestionnaireDto update(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id, @NotNull @RequestBody LeadQuestionnaireDto lead) {
        return leadQuestionnaireService.update(id, lead);
    }

    @DeleteMapping("/{id}")
    public void delete(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id) {
        leadQuestionnaireService.delete(id);
    }
}