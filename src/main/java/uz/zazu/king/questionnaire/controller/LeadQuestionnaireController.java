package uz.zazu.king.questionnaire.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.questionnaire.dto.LeadQuestionnaireDto;
import uz.zazu.king.questionnaire.service.LeadQuestionnaireService;

import java.util.List;


/**
 * Обрабатывает операции, связанные с опросниками по анкете custdev.
 * Включает следующие опросники:
 * - ДОУ АлАнга
 * - ДОУ Мирабад
 * - Почемучка АлАнга
 */
@RestController
@RequestMapping("/api/leads/questionnaire")
@RequiredArgsConstructor
public class LeadQuestionnaireController {

    private final LeadQuestionnaireService leadQuestionnaireService;

    @PostMapping
    public LeadQuestionnaireDto create(@Valid @RequestBody LeadQuestionnaireDto lead) {
        return leadQuestionnaireService.create(lead);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadQuestionnaireDto> getById(@NotBlank @PathVariable String id) {
        try {
            return ResponseEntity.ok(leadQuestionnaireService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<LeadQuestionnaireDto> getAll() {
        return leadQuestionnaireService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadQuestionnaireDto> update(@NotBlank @PathVariable String id, @NotNull @RequestBody LeadQuestionnaireDto lead) {
        try {
            return ResponseEntity.ok(leadQuestionnaireService.update(id, lead));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NotBlank @PathVariable String id) {
        try {
            leadQuestionnaireService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}