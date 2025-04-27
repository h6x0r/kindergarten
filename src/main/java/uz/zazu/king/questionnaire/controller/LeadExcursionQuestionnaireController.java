package uz.zazu.king.questionnaire.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.zazu.king.questionnaire.dto.LeadExcursionQuestionnaireDto;
import uz.zazu.king.questionnaire.service.LeadExcursionQuestionnaireService;

import java.util.List;

import static uz.zazu.king.common.Constant.ID_MUST_NOT_BE_NULL_MSG;


@RestController
@RequestMapping("/api/questionnaire/leads/excursion")
@RequiredArgsConstructor
public class LeadExcursionQuestionnaireController {

    private final LeadExcursionQuestionnaireService leadQuestionnaireService;

    @PostMapping
    public LeadExcursionQuestionnaireDto create(@Valid @RequestBody LeadExcursionQuestionnaireDto lead) {
        return leadQuestionnaireService.create(lead);
    }

    @GetMapping("/{id}")
    public LeadExcursionQuestionnaireDto getById(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id) {
        return leadQuestionnaireService.findById(id);
    }

    @GetMapping
    public List<LeadExcursionQuestionnaireDto> getAll() {
        return leadQuestionnaireService.findAll();
    }

    @PutMapping("/{id}")
    public LeadExcursionQuestionnaireDto update(
            @NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id,
            @NotNull @RequestBody LeadExcursionQuestionnaireDto lead) {
        return leadQuestionnaireService.update(id, lead);
    }

    @DeleteMapping("/{id}")
    public void delete(@NotBlank(message = ID_MUST_NOT_BE_NULL_MSG) @PathVariable String id) {
        leadQuestionnaireService.remove(id);
    }
}